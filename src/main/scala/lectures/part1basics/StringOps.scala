package lectures.part1basics

object StringOps extends App {
  val s: String = "Hello John Doe ??? !!! ---"

  println(s.replaceFirst("ell", " "))

  val aNumStr: String = "123"
  aNumStr.toInt
  val plusStr = aNumStr + 'z'
  val plusStr1 = aNumStr :+ 'z'
  println(plusStr); println(plusStr1)

  // STRING INTERPOLATORS

  val name = "John"
  val age = 10
  val greeting = s"Next year $name will be ${age + 1} years old"
  println(greeting)

  val speed = 1.2F
  val myth = f"$name%s can eat $speed%2.3f burgers per second!"
  println(myth)

  val rawText = raw"This has a \n newline"
  println(rawText)
  val escaped = "This also has a \n newline"
  println(raw"$escaped")
}
