package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 * 3
  println(s"x = $x")
  println(x == 7)

  var aVariable = 10

  // Instructions (Do) vs Expressions (Value)
  // If expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 3 else 5
  println(aConditionedValue)

  // Everything in Scala is an expression (?)
  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // Code blocks
  // The value of the block is the value of its last expression
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hi" else "whatsup"
  }
  println(aCodeBlock)

  val printHelloWorld = println("Hello world")
  print(printHelloWorld)
}
