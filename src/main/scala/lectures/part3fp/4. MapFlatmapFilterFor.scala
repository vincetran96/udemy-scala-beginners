package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(x => x + 1))

  println(list.filter(x => x % 2 == 0))

  println(list.flatMap(x => List(x, x * 10)))

  // iterating
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c')
  numbers.map(n => chars.map(c => println(s"($c, $n)")))
  val combos = numbers.flatMap(n => chars.map(c => s"$c, $n"))
  println(combos)

  // for each
  list.foreach(println)

  // for-comprehension
  // can use if to filter
  val forCombos = for {
    n <- numbers if n % 2 == 0
    c <- chars
  } yield s"$c, $n"
  println(forCombos)

  // syntax overload
  println(list.map {x =>
    x * 2
  })
}
