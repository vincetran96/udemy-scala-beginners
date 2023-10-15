package lectures.part2oop

object AbstractDataTypes extends App {
  // Abstract class
  // Can have both abstract and non-abstract members
  abstract class Animal {
    val creatureType: String = "wild"
    def eat(thing: String): Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "K9"
    override def eat(thing: String): Unit = println(s"Dog eats ${thing}")
  }

  // Traits
  // Can have both abstract and non-abstract members
  trait Carnivore {
    def eat(animal: Animal): Unit
    final val preferredMeal: String = "Fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    def eat(thing: String): Unit = println(s"Crocodile eats ${thing}")
    def eat(animal: Animal): Unit = println(s"Crocodile eats ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc eat dog

  // Traits vs abstract classes
  // 1 - Before Scala 3, traits do NOT have constructor params
  // 2 - You can mix multiple traits
  // 3 - Traits are behaviors, abstract classes are types of things
}
