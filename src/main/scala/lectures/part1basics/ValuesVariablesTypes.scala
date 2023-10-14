package lectures.part1basics

import scala.math.Pi

object ValuesVariablesTypes extends App {
  // Vals
  val x: Int = 42
  println(x)

  val aChar: Char = 'a'
  val aString: String = "hello"
  val name: String = "John"
  val age: Int = 35
  println(aChar)
  println(s"$name is $age years old")
  val height: Double = 1.9
  val name1: String = "James"
  println(f"$name1%s is $height%f meters tall")

  val aLong: Long = 123123123123123123L
  val aFloat: Float = 2.00F
  val aDouble: Double = 3.14
  println("The math Pi constant is: %.5f".format(Pi))
  println(Pi - aFloat)
  val aBool: Boolean = true
  println(s"Boolean value is $aBool")

  // vals are immutable
  // compiler can infer types


  // Vars
  var aVar = 5
  aVar = 10
}
