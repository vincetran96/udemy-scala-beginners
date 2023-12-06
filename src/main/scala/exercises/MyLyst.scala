package exercises

import scala.annotation.targetName


abstract class MyLyst[+T] {
  def head: T
  def tail: MyLyst[T]
  def isEmpty: Boolean
  def add[S >: T](element: S): MyLyst[S]
  def printElements: String
//  override def toString: String = "[" + printElements + "]"

  def map[V](transformer: (T) => V): MyLyst[V]
  def flatmap[V](transformer: (T) => MyLyst[V]): MyLyst[V]
  def filter(predicate: (T) => Boolean): MyLyst[T]

  // Concatenation
  def ++[S >: T](list: MyLyst[S]): MyLyst[S]
}


// Implements case class
case object Empty extends MyLyst[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyLyst[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[S >: Nothing](element: S): MyLyst[S] = new Cons(element, Empty)
  def printElements: String = ""

  def map[V](transformer: (Nothing) => V): MyLyst[V] = Empty

  def flatmap[V](transformer: (Nothing) => MyLyst[V]): MyLyst[V] = Empty

  def filter(predicate: (Nothing) => Boolean): MyLyst[Nothing] = Empty

  // Concatenation
  def ++[S >: Nothing](list: MyLyst[S]): MyLyst[S] = list
}


// Implements case class
case class Cons[+T](h: T, t: MyLyst[T]) extends MyLyst[T] {
  def head: T = h
  def tail: MyLyst[T] = t
  def isEmpty: Boolean = false
  def add[S >: T](element: S): MyLyst[S] = new Cons(element, this)
  def printElements: String = {
    if (this.t.isEmpty) this.h.toString
    else {
      this.h.toString + " " + this.t.printElements
    }
  }

  def map[V](transformer: (T) => V): MyLyst[V] = {
    new Cons(transformer(h), t.map(transformer))
  }

  def flatmap[V](transformer: (T) => MyLyst[V]): MyLyst[V] = {
    transformer(h) ++ t.flatmap(transformer)
  }

  def filter(predicate: T => Boolean): MyLyst[T] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  // Concatenation
  def ++[S >: T](list: MyLyst[S]): MyLyst[S] = {
    new Cons(h, t ++ list)
  }
}


// Must use [-T] here due to some arcane Scala reason
// Don't need these anymore since we use function types
//trait MyPredicate[-T] {
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-T, V] {
//  def transform(elem: T): V
//}


class Animal


class Dog extends Animal


object TestMyList extends App {
  val listInt: MyLyst[Int] = new Cons(1, new Cons(2, Empty))
  println(listInt.tail)
  val listInt1: MyLyst[Int] = listInt.add(3)
  println(listInt1)
  println(listInt1.tail)

  val listStr = Empty
  val listStr1 = listStr.add("hello")
  val listStr2 = listStr1.add("hi")

  println(listInt1.map(
    (elem: Int) => elem * 10
  ))

  println(listInt1.filter(
    (elem: Int) => elem > 2
  ))

  println(listInt1.++(listInt))

  println(listInt1.flatmap(
    (elem: Int) => new Cons(elem, new Cons(elem * 10, Empty))
  ))

  // See why we need [+T] in signature of Cons
  val listAnimals: Cons[Animal] = new Cons[Dog](new Dog, Empty)
  println(listAnimals)
}
