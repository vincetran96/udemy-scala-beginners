package lectures.part1basics

object CBNvsCBV extends App {
  // Call by value
  def callByValue(x: Long): Unit = {
    println(s"x is: $x")
    println(s"x is: $x")
  }

  // Call by name
  def callByName(x: => Long): Unit = {
    println(s"x is: $x")
    println(s"x is: $x")
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infRec(): Int = 1 + infRec()
  def printFirst(x:Int, y: => Int): Unit = println(x)

  printFirst(1, infRec())
}
