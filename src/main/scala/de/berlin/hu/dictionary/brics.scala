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

import dk.brics.automaton._
import java.io.{File, FileInputStream}

/**
 * User: Tim Rocktaeschel
 * Date: 6/28/12
 * Time: 11:44 AM
 */

object brics extends App {
  val automaton = Automaton.load(new FileInputStream(new File(args(0))))
  //val automaton = BasicAutomata.makeString("water")
  val text = args(1)
  //val text = "This is a test sentence containing the word water. I would love a glas of water now."
  val runAutomaton = new RunAutomaton(automaton)
  val matcher:AutomatonMatcher = runAutomaton.newMatcher(text)
  while (matcher.find) {
    println(matcher.start, matcher.end)
  }
}