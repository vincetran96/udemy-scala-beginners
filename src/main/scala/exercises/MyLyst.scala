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
  def forEach(fn: (T) => Unit): Unit
  def sort(fn: (x: T, y: T) => Int): MyLyst[T]

  // Concatenation
  def ++[S >: T](list: MyLyst[S]): MyLyst[S]
}


// Implements case class
case object Empty extends MyLyst[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyLyst[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[S >: Nothing](element: S): MyLyst[S] = new Cons(element, Empty)
  override def printElements: String = ""

  override def map[V](transformer: (Nothing) => V): MyLyst[V] = Empty

  override def flatmap[V](transformer: (Nothing) => MyLyst[V]): MyLyst[V] = Empty

  override def filter(predicate: (Nothing) => Boolean): MyLyst[Nothing] = Empty

  override def forEach(fn: Nothing => Unit): Unit = { }

  override def sort(fn: (x: Nothing, y: Nothing) => Int): MyLyst[Nothing] = Empty

  // Concatenation
  override def ++[S >: Nothing](list: MyLyst[S]): MyLyst[S] = list
}


// Implements case class
case class Cons[+T](h: T, t: MyLyst[T]) extends MyLyst[T] {
  override def head: T = h
  override def tail: MyLyst[T] = t
  override def isEmpty: Boolean = false
  override def add[S >: T](element: S): MyLyst[S] = new Cons(element, this)
  override def printElements: String = {
    if (this.t.isEmpty) this.h.toString
    else {
      this.h.toString + " " + this.t.printElements
    }
  }

  override def map[V](transformer: (T) => V): MyLyst[V] = {
    new Cons(transformer(h), t.map(transformer))
  }

  override def flatmap[V](transformer: (T) => MyLyst[V]): MyLyst[V] = {
    transformer(h) ++ t.flatmap(transformer)
  }

  override def filter(predicate: T => Boolean): MyLyst[T] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def forEach(fn: (T) => Unit): Unit = {
    fn(this.h)
    if (!this.t.isEmpty) this.t.forEach(fn)
  }

  override def sort(fn: (x: T, y: T) => Int): MyLyst[T] = {
    def swap(
      lyst: MyLyst[T],
      return_now: Boolean = false
    ): (MyLyst[T], Boolean) =
    {
      if (lyst.tail.isEmpty | return_now) {
        println(s"Tail of lyst = ${lyst.printElements} is Empty")
        (lyst, true)
      }
      else {
        if (fn(lyst.head, lyst.tail.head) > 0) {
          val (newTail, newEnded) = swap(new Cons(lyst.head, lyst.tail.tail), return_now)
          val newLyst = new Cons(lyst.tail.head, newTail)
          if (newEnded) (newLyst, false)
          else swap(newLyst, false)
        }
        else {
          val (newTail, newEnded) = swap(lyst.tail, return_now)
          val newLyst = new Cons(lyst.head, newTail)
          if (newEnded | newTail.head == lyst.tail.head) (newLyst, false)
          else swap(newLyst, false)
        }
      }
    }
    val(swappedLyst, return_now) = swap(this)
    swappedLyst
  }

  // Concatenation
  override def ++[S >: T](list: MyLyst[S]): MyLyst[S] = {
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
  val listInt: MyLyst[Int] = new Cons(2, new Cons(1, Empty))
//  println(listInt.tail)
  val listInt1: MyLyst[Int] = listInt.add(100)
//  println(listInt1)
//  println(listInt1.tail)

  val listStr = Empty
  val listStr1 = listStr.add("hello")
  val listStr2 = listStr1.add("hi")

//  println(listInt1.map(
//    (elem: Int) => elem * 10
//  ))
//
//  println(listInt1.filter(
//    (elem: Int) => elem > 2
//  ))

//  println(listInt1.++(listInt))

//  println(listInt1.flatmap(
//    (elem: Int) => new Cons(elem, new Cons(elem * 10, Empty))
//  ))

  println(listInt1.printElements)
//  listInt1.forEach(x => println(x))

  val sortedListInt1 = listInt1.sort((x: Int, y: Int) => x - y)
  println(sortedListInt1.printElements)

  // See why we need [+T] in signature of Cons
//  val listAnimals: Cons[Animal] = new Cons[Dog](new Dog, Empty)
//  println(listAnimals)
}
