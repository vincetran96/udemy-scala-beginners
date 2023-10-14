package lectures.part2oop

object Inheritance extends App {
  // Single-class inheritance
  // Access modifiers can change method visibility
  class Animal {
    val isDomestic: Boolean = false

    private def gene(): Unit = println("Animal")
    protected def eat(): Unit = println("Nomnom")
    def move(): Unit = println("Animal moves")

    final def exist(): Unit = println("I exist")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      this.eat()
      println("Cat goes crunch")
    }
  }

  val cat = new Cat
  cat.crunch()


  // For classes with constructors with params
  // the "Extends" must be similar to the super class
  class Person(name: String, age: Int) {
    // Can also have auxiliary constructor
    def this(name: String) = this(name, 0)
  }

  // Sub classes must be defined like so
  class Adult(name: String, age: Int, idCard: String) extends Person(name, 18)
  class Child(name: String, age: Int, careTaker: Adult) extends Person(name)


  // Overriding
  class Dog extends Animal {
    override val isDomestic: Boolean = true

    override def eat(): Unit = {
      super.eat()
      println("Mrrmrr")
    }
    override def move(): Unit = println("Dog moves")
  }

  val dog = new Dog
  println(dog.isDomestic)
  dog.eat()

  // Can override class fields in the constructor
  class Chicken(override val isDomestic: Boolean) extends Animal

  val chicken = new Chicken(true)
  println(chicken.isDomestic)


  // Type substitution (Polymorphism)
  // In this case the instance uses the
  // overriden method from Dog
  val unknownAnimal: Animal = new Dog
  unknownAnimal.move()

  // Call method from super class
  dog.eat()

  // Prevent overriding
  // - Use final keyword on member (e.g., field, method)
  // - Use final keyword on the entire class
  // - Seal the class, only allow extending the class in THIS FILE
}
