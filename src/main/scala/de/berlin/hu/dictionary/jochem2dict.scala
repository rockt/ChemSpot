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
import dk.brics.automaton.{BasicOperations, BasicAutomata}
import collection.JavaConversions._
import java.io.{PrintWriter, FileOutputStream}

/**
 * User: Tim Rocktaeschel
 * Date: 6/21/12
 * Time: 4:01 PM
 */

object jochem2dict extends App {
  val dictionary = Source.fromFile(args(0)).getLines()
  val automatonOutput =  new FileOutputStream(args(1))
  val idMapOutput = new PrintWriter(args(2))

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
      if (term.length > 2) idMapOutput.println(cas + ":" + inChI + "\t" + term)
      chemicals = term :: chemicals
    }
  }
  idMapOutput.close()

  val automata = chemicals.map((name: String) => BasicAutomata.makeString(name))
  val automaton = BasicOperations.union(automata)
  automaton.store(automatonOutput)
}