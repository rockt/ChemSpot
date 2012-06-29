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
  if (args.length != 4) {
    println("Usage: jochem2automat jochem.ontology output.automat output.ids numberOfThreads")
    exit(-1)
  }

  println("Reading Jochem dictionary...")
  val dictionary = Source.fromFile(args(0)).getLines()
  val automatonOutput =  new FileOutputStream(args(1))
  val idMapOutput = new PrintWriter(args(2))
  val numberOfThreads = args(3).toInt
  var chemicals = List[String]()

  val records = dictionary.mkString("\n").split("\n--\n").drop(12)
  for (record <- records) {
    val lines = record.split("\n")
    val casOption = lines.find((s: String) => s.startsWith("DB CAS_"))
    val cas = if (casOption.isDefined) casOption.get.substring(7) else ""
    val inChIOption = lines.find((s: String) => s.startsWith("DB INCH_"))
    val inChI = if (inChIOption.isDefined) inChIOption.get.substring(8) else ""
    val terms = lines.filter((s: String) => s.startsWith("TM ")).map((t: String) => t.split("\t")(0).substring(3))
    for (term <- terms) {
      if (term.length > 2) idMapOutput.println(term + "\t" + cas + "\t" + inChI)
      chemicals = term :: chemicals
    }
  }
  idMapOutput.close()

  println("Generating automata...")
  val automata = chemicals.map((name: String) => BasicAutomata.makeString(name))

  class Merger(val n:Int) extends Actor {
    var automaton:Automaton = null
    var automata:List[Automaton] = Nil
    def act() {
      loop {
        react {
          case a:Automaton => {
            a :: automata
          }
          case as:List[Automaton] => {
            as ++ automata
          }
          case "merge" => {
            System.out.println("\t\tActor " + n + " starts merging " + automata.size + " automata...")
            automaton = BasicOperations.union(automata)
          }
          case "exit" => {
            System.out.println("\t\tActor " + n + " finished!")
            reply(n + "finished")
            exit()
          }
        }
      }
      if (automaton != null && mailboxSize == 0) exit()
    }
    def getAutomaton = automaton
  }

  println("Merging " + automata.size + " into one automata using " + numberOfThreads + " threads...")
  //initializing actors
  val mergers = for (i <- 0 until numberOfThreads) yield new Merger(i)
  mergers.foreach((m:Merger) => m.start())
  System.out.println("\tDistributing automata...")
  for (i <- 0 until automata.size) mergers(i % numberOfThreads) ! automata(i)
  mergers.foreach((m:Merger) => m ! "merge")
  //wait for actors to finish
  mergers.foreach((m:Merger) => m !? "exit")
  System.out.println("\tGetting automaton from Actors and merging into a single autotmaton...")
  val automaton = BasicOperations.union(mergers.map((m:Merger) => m.getAutomaton))
  System.out.println("\tFinished merging!")
  println("Removing dead transitions...")
  automaton.removeDeadTransitions()
  println("Minimizing autotmaton...")
  automaton.minimize()
  println("Generating RunAutomaton...")
  val runAutomaton = new RunAutomaton(automaton) //TODO: perhaps tablelize
  println("Storing RunAutomaton to " + args(1))
  runAutomaton.store(automatonOutput)
  println("Finished!")
}