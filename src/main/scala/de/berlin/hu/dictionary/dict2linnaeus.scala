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

/**
 * User: Tim Rocktaeschel
 * Date: 6/21/12
 * Time: 4:01 PM
 */

class Entry(terms: Set[String], cas: String, inChI: String)

object dict2linnaeus extends App {
  val dictionary = Source.fromFile("../ChemlistV1_2.ontology").getLines()
  val records = dictionary.mkString("\n").split("\n--\n").drop(12)
  for (record <- records) {
    val lines = record.split("\n")
    val cas = lines.find((s: String) => s.startsWith("DB CAS_")).getOrElse("")
    val inChI = lines.find((s: String) => s.startsWith("DB INCH_")).getOrElse("")
    val terms = lines.filter((s: String) => s.startsWith("TM ")).map((t: String) => t.split("\t")(0).substring(3))
    println("%s\t%s\t%s".format(cas, inChI, terms.mkString("\n\t", "\n\t", "")))
  }

}