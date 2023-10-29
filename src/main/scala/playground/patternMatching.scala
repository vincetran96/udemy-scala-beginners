package playground

import scala.util.Random

object patternMatching extends App {
  val x: Int = Random.nextInt()

  x match
    case 0 => println("zero")
    case 1 => println("one")
    case _ => println("haha")
}
