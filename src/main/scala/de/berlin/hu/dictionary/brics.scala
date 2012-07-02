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
import java.io.{FileOutputStream, File}
import de.berlin.hu.uima.ae.tagger.linnaeus.BricsMatcher
import de.berlin.hu.chemspot.Mention
import scala.collection.JavaConversions._

/**
 * User: Tim Rocktaeschel
 * Date: 6/28/12
 * Time: 11:44 AM
 */

object brics extends App {
  automatonWriterPrototype()
  automatonMatcherPrototype()

  def automatonWriterPrototype() {
    val a1 = BasicAutomata.makeStringUnion(
      "alpha", "beta", "gamma"
    )
    val a2 = BasicAutomata.makeStringUnion(
      "epsilon", "theta", "chi"
    )

    a1.removeDeadTransitions()
    a1.minimize()
    a2.removeDeadTransitions()
    a2.minimize()

    val r1 = new RunAutomaton(a1)
    val r2 = new RunAutomaton(a2)
    r1.store(new FileOutputStream(new File("../r1")))
    r2.store(new FileOutputStream(new File("../r2")))
  }

  def automatonMatcherPrototype() {
    val matcher:BricsMatcher = new BricsMatcher("../r.zip")
    val matches = matcher.`match`("This is a sample text containing the words alpha and theta.").toList.sortWith(
      (a: Mention, b: Mention) => a.getStart <= b.getStart
    )

    for (mention:Mention <- matches) {
      println(mention.getStart, mention.getEnd)
    }
  }
}