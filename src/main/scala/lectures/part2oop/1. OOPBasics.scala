package lectures.part2oop

object OOPBasics extends App {
  val person = new Person("John", 20)
  println(s"The person's age is ${person.age}")

  person.greet("James")
  person.greet(100)
  person.age = 25
  println(s"The person's new age is now ${person.age}")

  val writer = new Writer("John", "Doe", 1990)
  println(writer.fullName())

  val anotherWriter = new Writer("John", "Doe", 1990)
  println("Checking if 2 writers are the same")
  println(writer == anotherWriter)

  val novel = new Novel("A great novel", 2020, writer)
  val novelCopy = novel.copy(2022)
  println("Doing Novel stuff")
  println(novel.isWrittenBy(anotherWriter))
  println(s"Novel is written in ${novel.releaseYear}")
  println(novelCopy.isWrittenBy(anotherWriter))
  println(s"NovelCopy is written in ${novelCopy.releaseYear}")

  val counter = new Counter(1)
  val newCounter = counter.inc(10)
  counter.inc(5).print()
}

// constructor
class Person(name: String, var age: Int) {
  println("Creating a Person")

  val x = 2

  def greet(name: String): Unit = {
    println(s"${this.name} says hello $name")
  }

  // method overload
  def greet(i: Int): Int = {
    println("I'm returning an Int instead")
    i
  }

  // constructor overload
  def this(name: String) = this(name, 10)
}

// class parameters are NOT FIELDS

class Writer(
  firstName: String,
  surName: String,
  val birthYear: Int) {

  def fullName(): String = firstName + " " + surName

}

class Novel(
  name: String,
  val releaseYear: Int,
  author: Writer) {

  def authorAge(): Int = releaseYear - author.birthYear

  def isWrittenBy(author: Writer): Boolean = {
    this.author == author
  }

  def copy(newReleaseYear: Int): Novel = {
    new Novel(this.name, newReleaseYear, this.author)
  }
}

// Immutability and side effect source
class Counter(
  val count: Int = 0) {

  def inc(): Counter = {
    println("Incrementing by 1")
    new Counter(this.count + 1) // IMMUTABILITY CONCEPT
  }

  def dec(): Counter = {
    println("Decrementing by 1")
    new Counter(this.count - 1)
  }

  // implement with inc/dec as source of side effect
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc().inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec().dec(n - 1)
  }

  def print(): Unit = println(this.count)
}