package lectures.part2oop

object Objects extends App {
  // Companion object
  // Object = singleton instance
  object Person {
  val N_EYES: Int = 2
  def canFly: Boolean = false

  // Factory method
  def from(mom: Person, dad: Person, name: String): Person = {
    new Person(name, 0)
  }
  def apply(caller: Person): String = s"I'm called by ${caller.name}"
  }

  // Instance-level functionality
  class Person(val name: String, age: Int) {
    def learns(thing: String): String = {
      s"${this.name} learns $thing"
    }
    def describe(): String = {
      s"${this.name} has ${Person.N_EYES} eyes"
    }
  }

  val mary = new Person("John", 21)
  val john = new Person("John", 21)
  println(mary == john)

  val p0 = Person
  val p1 = Person
  println(p0 == p1)

  val bob = Person.from(mary, john, "Bob")
  println(bob.describe())
  println(Person(bob))

  // Scala application = Scala object with
  // def main(args: Array[String]): Unit
}
