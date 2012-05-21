import collection.mutable.HashMap
import io.Source
import java.io.{InputStreamReader, File, FileInputStream}
import java.lang.IllegalStateException
import java.util.zip.GZIPInputStream

def getMentions(corpus: Iterator[String]) : HashMap[String, List[(Int,Int,String,String)]] = {
  //begin, end, label, text
  val mentions = new HashMap[String, List[(Int,Int,String,String)]]
  var firstLine = false
  var offset = 0
  var pmid = ""
  var beginOfEntity = 0
  var lastLabel = "O"
  var endOfLastToken = 0
  var lastLabelType = ""
  var entityText = ""

  for (line:String <- corpus) {
    if (line.startsWith("###")) {
      pmid = line.substring(4)
      if (!mentions.contains(pmid)) mentions.put(pmid, Nil)
      firstLine = true
    }
    else if (!line.isEmpty) {
      val splits = line.split("\t")
      val tokenText = splits(0)
      val begin = splits(1).toInt.intValue()
      val end = splits(2).toInt.intValue()
      var label = splits(4)(1) + ""
      val labelType = if (label == "O") "" else splits(4).substring(3)
      //ignore MODIFIER entities
      if (labelType == "MODIFIER") label = "O"

      if (firstLine) {
        offset = begin
        firstLine = false
      }

      (lastLabel, label) match {
        case ("O","B") => {
          beginOfEntity = begin
          entityText = tokenText
        }
        case ("O","O") => //do nothing
        case ("B","B") => {
          addMention(beginOfEntity)
          beginOfEntity = begin
          entityText = tokenText
        }
        case ("B","I") => entityText += " " + tokenText
        case ("B","O") => addMention(beginOfEntity)
        case ("I","B") => {
          addMention(beginOfEntity)
          beginOfEntity = begin
          entityText = tokenText
        }
        case ("I","I") => entityText += " " + tokenText
        case ("I","O") => addMention(beginOfEntity)
        case _ => System.err.println("WARNING: found illegal transition from " + lastLabel + " to " + label + ": " + pmid + " " + begin)
      }

      def addMention(begin:Int) {
        if (mentions.contains(pmid)) mentions(pmid) = (begin-offset, endOfLastToken-offset, lastLabelType, entityText) :: mentions(pmid)
        else mentions.put(pmid, List((begin-offset, endOfLastToken-offset, lastLabelType, entityText)))
      }

      lastLabel = label
      lastLabelType = labelType
      endOfLastToken = end
    }
  }  
  mentions
}



val goldCorpus = if (args(0).endsWith("gz")) Source.fromInputStream(new GZIPInputStream(new FileInputStream(new File(args(0)))), "ISO-8859-1").getLines else Source.fromFile(args(0), "ISO-8859-1").getLines
val predictCorpus = if (args(1).endsWith("gz")) Source.fromInputStream(new GZIPInputStream(new FileInputStream(new File(args(1)))), "ISO-8859-1").getLines else Source.fromFile(args(1), "ISO-8859-1").getLines

val gold = getMentions(goldCorpus)
val predict = getMentions(predictCorpus)

for (key <- gold.keys) if (!predict.contains(key)) throw new IllegalStateException(key)

var fps = new HashMap[String, List[(Int,Int,String,String)]]
var fns = new HashMap[String, List[(Int,Int,String,String)]]

var tp,fp,fn = 0.0


for (key <- predict.keys; tuple <- predict(key)) {
  val begin = tuple._1
  val end = tuple._2
  //is our predicted entity also an entity in the gold standard?
  if (gold(key).find((t:(Int, Int, String, String)) =>  t._1 == begin && t._2 == end).isDefined) {
    //if yes, it is a true positive
    tp += 1
  } else {
    //if not, it is a false positive
    fp += 1
    if (fps.contains(key)) fps(key) = tuple :: fps(key) else fps.put(key, List(tuple))
  }
}

for (key <- gold.keys; tuple <- gold(key)) {
  val begin = tuple._1
  val end = tuple._2
  //did we miss any mention of the gold standard?
  if (!predict(key).find((t:(Int, Int, String, String)) =>  t._1 == begin && t._2 == end).isDefined) {
    fn += 1
    if (fns.contains(key)) fns(key) = tuple :: fns(key) else fns.put(key, List(tuple))
  }
}

println
for (key <- predict.keys) {
  println("PMID: " + key)
  if (fps.contains(key)) println(fps(key).map((x:(Int, Int, String, String)) => (x._4, x._1, x._2)).mkString("FP ", "\nFP ", ""))
  if (fns.contains(key)) println(fns(key).map((x:(Int, Int, String, String)) => (x._4, x._1, x._2)).mkString("FN ", "\nFN ", ""))
  println
}
println

val p = if ((tp + fp) == 0) 1.0 else tp / (tp + fp)
val r = if ((tp + fn) == 0) 0.0 else tp / (tp + fn)
val f1 = if ((p + r) == 0) 0.0 else 2 * (p * r) / (p + r)

println("True Positives:\t\t%s\nFalse Positives:\t%s\nFalse Negatives:\t%s".format(tp,fp,fn))
println("Precision:\t\t\t%f\nRecall:\t\t\t\t%f\nF1 Score:\t\t\t%f".format(p,r,f1))
