package lectures.part2oop

object MethodNotations extends App{
  class Person(val name: String, favMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == this.favMovie
    def +(person: Person): String = {
      s"${this.name} hangs out with ${person.name}"
    }
    def +(nickname: String): Person = {
      new Person(s"${this.name} the ${nickname}", this.favMovie)
    }
    def learns(thing: String): String = s"${this.name} learns ${thing}"
    def learnsScala(): String = this learns "Scala"

    def unary_! : String = s"${this.name}, yo!"
    def unary_+ : Person = {
      new Person(this.name, this.favMovie, this.age + 1)
    }

    def isAlive: Boolean = true

    /**
     * @param n
     * @return a new string
     */
    def apply(): String = s"My name = ${this.name} and favMovie = ${this.favMovie}"
    def apply(n: Int): String = s"${this.name} watched ${this.favMovie} $n times"
  }

  // Infix notation
  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  val tom = new Person("Tom", "Batman")
  println(mary.+(tom))
  println(mary + tom)

  println(1.+(2))

  // ALL OPERATORS ARE METHODS IN SCALA

  // Prefix notation
  val x = -1
  val y = 1.unary_-
  println(s"y = $y")

  // unary_prefix only works with + - ~ !

  println(!mary)

  // Postfix notation
  // Must enable scala.language.postfixOps to use it
//  println(mary isAlive)

  // Apply method
  println(mary())

  val maryNickname = mary + "desolator"
  println(maryNickname.name)

  val maryOlder = +mary
  println(maryOlder.age)

  println(mary learns "Piano")
  println(mary.learnsScala())

  println(mary(2))

}
