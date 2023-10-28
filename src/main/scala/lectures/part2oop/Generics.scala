package lectures.part2oop

object Generics extends App {
  class MyList[A] {
    // Uses the type A
    def add[B >: A](element: B): MyList[B] = new MyList[B]
  }

  class MyMap[Key, Value]

  val listOfInts: MyList[Int] = new MyList[Int]
  val listOfStrings: MyList[String] = new MyList[String]

//  object MyList {
//    def empty[A]: MyList[A] = ???
//  }
//  val emptyListOfInts: MyList[Int] = MyList.empty[Int]

  // Variance
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Covariant
  // List[Cat] extends List[Animal]
  // What if we want animalList.add(new Dog) ???
  // Then the list will become a list of Animals
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // Invariant
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // Contravariant
  class ContravariantList[-A]
  val contravariantAnimalList: ContravariantList[Cat] = new ContravariantList[Animal]
  // This doesn't make mush sense, because not all Animals are Cats
  // However...
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // Now this makes more sense because a Trainer of Animals can also be a Trainer of Cats


  // Bounded types
  // <: means "subtype of ..."
  // >: means "supertype of ..."
  class Vehicle
  class Cage[A <: Animal](animal: Animal) // Only accepts subtypes of Animal
  val cage = new Cage[Dog](new Dog)
}
