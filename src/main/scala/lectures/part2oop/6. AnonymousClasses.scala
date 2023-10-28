package lectures.part2oop

object AnonymousClasses extends App   {
  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("lmao")
  }

  // This is equivalent to
  class zxcv$$anaon$1 extends Animal {
    override def eat: Unit = println("lmao")
  }

  val funnyAnimal1: zxcv$$anaon$1 = new zxcv$$anaon$1

  println(funnyAnimal.getClass)
  println(funnyAnimal1.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"hello, my name is $name")
  }

  val jim = new Person(name="Jim") {
    override def sayHi: Unit = println(s"This is Jim")
  }

  jim.sayHi


  trait MyPredicate[T] {
    def test(thing: T): Boolean
  }

  trait MyTransformer[A, B] {
    def transform(elem: A): B
  }

  class IntPredicate extends MyPredicate[Int] {
    override def test(thing: Int): Boolean =
      if (thing > 10) true
      else false
  }

  val intP0 = new IntPredicate
  println(intP0.test(10))
}
