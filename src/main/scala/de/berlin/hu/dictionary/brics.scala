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
  //automatonWriterPrototype()
  //automatonMatcherPrototype()
  jochemMatcherPrototybe()

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

  def jochemMatcherPrototype() {
    val matcher = new BricsMatcher(args(0))
    for (mention <- matcher.`match`(
      """Hypersensitivity may occur shortly, within 15 minutes or longer, up to 24 hours after NSAID intake. In general it develops within 1 to 4 hours [3]. Some patients might have life-threatening reactions, especially those with aspirin-exacerbated respiratory diseases (AERDs, Widal syndrome), which associate aspirin sensitivity, asthma, nasal polyposis, and airway remodelling [1].
        |
        |In most patients the adverse reaction is nonallergic. Those with eicosanoid metabolism dysfunction or other alterations are prone to hypersensitivity when NSAIDs inhibit the enzyme cylooxygenase-1 (Cox-1) [3–14]. Selective NSAIDs strongly inhibit COX-2, but they are weak inhibitors of COX-1, so they are well tolerated in patients with NSAID-sensitive asthma or CIU [4, 5]. The concentration inhibiting efficiently COX-1 or COX-2 may differ as much as 3 logs between the strongest and weakest inhibitors (Table 1) [15–17]. Pharmacological profiles as well as hypersensitivity depend on their inhibitory activities."""
    )) {
      println(mention.getStart, mention.getEnd, mention.getText)
    }
  }
}