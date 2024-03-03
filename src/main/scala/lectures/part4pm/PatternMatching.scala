package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  val random = new Random
  val x = random.nextInt(10)
  val desc = x match {
    case 1 => "One"
    case 2 => "Two"
    case _ => 99  // Wildcard
  }
  println(desc)

  // Decompose value
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)
  val greet = bob match {
    case Person(n, a) if a < 21 => "Too young"  // Guard - additional condition for match
    case Person(n, a) => "Hi"
    case _ => "What are you?"
  }
  println(greet)

  /*
    Cases are matched IN ORDER (first match will return expr)
    If no cases match, then MatchError
    Type of the match is the UNION of all types in all cases
    Pattern matching works well with case classes
      - Because case classes come with extractor patterns out-of-the-box
   */

  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greet: String) extends Animal
  val animal: Animal = Dog("Canine")
  val animalMatch = animal match {
    case Dog(b) => s"Matched dog of breed ${b}"
  }
  println(animalMatch)

  // Exercise
  val ints = Seq(1, 2, 3)
  for {i <- ints} {
    if (i % 2 == 0) println(i)
    else println("Odd.")
  }

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr
  def show(e: Expr): String = {
    e match {
      case Number(i) => s"$i"
      case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
      case Prod(e1, e2) =>
        s"${e1 match {case Sum(_, _) => s"(${show(e1)})" case _ => s"${show(e1)}"}} " +
        s"* ${e2 match {case Sum(_, _) => s"(${show(e2)})" case _ => s"${show(e2)}"}}"
    }
  }
  val s = Sum(Number(2), Number(3))
  val r = Prod(Prod(s, Number(10)), Number(5))
  println(show(r))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
  println(show(Prod(Sum(Number(1), Number(2)), Sum(Number(3), Number(4)))))
}
