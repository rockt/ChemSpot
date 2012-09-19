/*
 * Copyright (c) 2012. Humboldt-Universität zu Berlin, Dept. of Computer Science and Dept.
 * of Wissensmanagement in der Bioinformatik
 * -------------------------------
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS COMMON PUBLIC
 * LICENSE ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM
 * CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 *
 * http://www.opensource.org/licenses/cpl1.0
 */

package de.berlin.hu.dictionary

import io.Source
import dk.brics.automaton.{Automaton, RunAutomaton, BasicOperations, BasicAutomata}
import collection.JavaConversions._
import java.io.{PrintWriter, FileOutputStream}
import actors.Actor

/**
 * User: Tim Rocktaeschel
 * Date: 6/21/12
 * Time: 4:01 PM
 */

object jochem2automat extends App {
  def getIds(lines:Array[String]):String = {
    (for (db <- List("CHID", "CHEB", "CAS", "PUBC", "PUBS", "INCH", "DRUG", "HMBD", "KEGG", "KEGD", "MESH")) yield {
      val option = lines.find((s: String) => s.startsWith("DB " + db))
      if (option.isDefined) option.get.substring(4 + db.length) else ""
    }).mkString("\t")
  }

  if (args.length != 4) {
    println("Usage: jochem2automat jochem.ontology output.automat output.ids numberOfThreads")
    exit(-1)
  }

  val start = System.currentTimeMillis()
  println("Reading Jochem dictionary...")
  val dictionary = Source.fromFile(args(0)).getLines()
  val idMapOutput = new PrintWriter(args(2))
  val numberOfThreads = args(3).toInt
  var chemicals = List[String]()

  val records = dictionary.mkString("\n").split("\n--\n").drop(12)
  for (record <- records) {
    val lines = record.split("\n")
    val terms = lines.filter((s: String) => s.startsWith("TM ")).map((t: String) => t.split("\t")(0).substring(3))
    val isChemIDplus = lines.filter((s: String) => s.startsWith("DB CHID")).length > 0
    for (term <- terms) {
      if (term.length > 2) idMapOutput.println(term.toLowerCase() + "\t" + getIds(lines))
      //only keep terms of the ChemIDplus dictionary
      if (term.length > 2 && isChemIDplus) chemicals = term :: chemicals
    }
  }
  idMapOutput.close()

  println("Generating automata...")
  //sorted for better compression when using several automata
  val automata = chemicals.sorted.map((name: String) => BasicAutomata.makeString(name))

  class Merger(val n:Int) extends Actor {
    var automaton:Automaton = null
    var automata:List[Automaton] = Nil
    def act() {
      loop {
        react {
          case a:Automaton => {
            automata.add(a)
          }
          case as:List[Automaton] => {
            if (automata.isEmpty) automata = as else automata.addAll(as)
          }
          case "merge" => {
            println("Actor " + n + " starts merging " + automata.size + " automata...")
            automaton = BasicOperations.union(automata)
          }
          case "exit" => {
            println("Actor " + n + " finished!")
            reply(n + "finished")
          }
        }
      }
      if (automaton != null && mailboxSize == 0) exit()
    }

    def getAutomaton = automaton
  }

  println("Start merging and storing " + automata.size + " automata into " + numberOfThreads + " automata...")
  //initializing actors
  val mergers = for (i <- 0 until numberOfThreads) yield new Merger(i)
  mergers.foreach((m:Merger) => m.start())
  println("Distributing automata...")
  val slices = automata.grouped(math.ceil(automata.size/numberOfThreads.toDouble).toInt).toList
  for (i <- 0 until numberOfThreads) mergers(i) ! slices(i)
  mergers.foreach((m:Merger) => m ! "merge")
  //wait for actors to finish
  mergers.foreach((m:Merger) => m !? "exit")
  println("Running garbage collection...")
  System.gc()
  //due to  main memory constraints, start processing iteratively
  println("Removing dead transitions...")
  val mergedAutomata = for (merger <- mergers) yield merger.getAutomaton
  for (i <- 0 until mergedAutomata.size) {
    print((100 * (i / mergedAutomata.size.toDouble)) + "%\r")
    mergedAutomata(i).removeDeadTransitions
  }
  println
  println("Running garbage collection...")
  System.gc()
  println("Minimizing automata...")
  for (i <- 0 until mergedAutomata.size) {
    print((100 * (i / mergedAutomata.size.toDouble)) + "%\r")
    mergedAutomata(i).minimize
    System.gc()
  }
  println
  println("Generating and storing RunAutomata...")
  for (i <- 0 until mergedAutomata.size) {
    print((100 * (i / mergedAutomata.size.toDouble)) + "%\r")
    val runAutomaton = new RunAutomaton(mergedAutomata(i))
    runAutomaton.store(new FileOutputStream(args(1) + "." + i))
  }
  println
  println("Finished after " + ((System.currentTimeMillis() - start) / 60000) + " minutes!")
}