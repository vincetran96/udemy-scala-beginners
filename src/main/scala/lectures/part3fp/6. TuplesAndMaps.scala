package lectures.part3fp

object TuplesAndMaps extends App{
  // Tuples
  val aTuple = Tuple2[Int, String](2, "sth") // Tuple2[Int, String] = (Int, String)
  println(aTuple._1)
  println(aTuple(0))
  println(aTuple.copy(_2 = "sth1"))

  val swapTuple = aTuple.swap
  println(swapTuple)


  // Maps (immutable)
  val phoneBook: Map[String, Int] = Map(
    ("A", 1),
    ("B", 2),
    ("C" -> 3)
  ).withDefaultValue(-1)
  println(phoneBook)

  println(phoneBook.contains("B"))
  println(phoneBook.get("D"))
  println(phoneBook("D"))

  val newContact = ("E", 5)
  val phoneBook1 = phoneBook + newContact
  println(phoneBook1)

  // `map` for Map returns Map
  println(phoneBook1.map((x, y) => x.toLowerCase -> y * 10))
  println(phoneBook1.map(pair => (pair._1.toLowerCase, pair._2 * 10)))
  println(phoneBook1.view.mapValues(v => v * 100).toMap)

  println(phoneBook1.view.filterKeys(k => k.startsWith("A")).toMap)

  // Conversion to other collections
  println(phoneBook1.toList)
  val tupToMap = List(aTuple, swapTuple).toMap
  println(tupToMap)

  // GroupBy
  val transactions: Map[String, Int] = Map(
    ("AA", 1),
    ("AB", 2),
    ("BA", 3),
    ("BB", 4)
  )
  transactions.foreach(println)
  // First way
  val transGb = transactions
    .groupBy(pair => pair._1.slice(0, 1))
    .view.mapValues(m => m.foldLeft(0)((acc, item) => acc + item._2))
    .toMap
  println(transGb)
  // Second way
  val transGb1 = transactions.toList
    .groupBy(pair => pair._1.slice(0, 1))
    .view.mapValues(l => l.foldLeft(0)((acc, elem) => acc + elem._2))
    .toMap
  println(transGb1)
}
