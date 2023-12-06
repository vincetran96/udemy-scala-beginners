package lectures.part2oop

object CaseClasses extends App{
  /*
  case classes implement equals, hashCode, toString
   */
  case class Person(name: String, age: Int)

  // Class params are fields (public vals)
  val jim: Person = new Person("Jim", 30)
  println(jim.name)

  // Pre-implemented and nice toString method
  println(jim)

  // Pre-implemented equals and hashCode method
  val jim1: Person = new Person("Jim", 30)
  println(jim == jim1)

  // Case classes have a copy method
  val jim2: Person = jim.copy(age = 35)
  println(jim2)

  // Case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 25)

  // Case classes are serializable, useful to pass around data

  // Case classes have extractor patterns
  
  // There are also case objects
}
