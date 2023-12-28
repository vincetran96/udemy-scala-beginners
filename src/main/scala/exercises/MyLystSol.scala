package exercises

import scala.annotation.targetName


abstract class MyLystSol[+T] {
  def head: T
  def tail: MyLystSol[T]
  def isEmpty: Boolean
  def add[S >: T](element: S): MyLystSol[S]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[V](transformer: (T) => V): MyLystSol[V]
  def flatmap[V](transformer: (T) => MyLystSol[V]): MyLystSol[V]
  def filter(predicate: (T) => Boolean): MyLystSol[T]
  def forEach(fn: (T) => Unit): Unit
  def sort(fn: (x: T, y: T) => Int): MyLystSol[T]
  def zipWith[T1, V](other: MyLystSol[T1], fn: (x: T, y: T1) => V): MyLystSol[V]
  def fold[T1](start: T1, fn: (x: T1, y: T) => T1): T1

  // Concatenation
  def ++[S >: T](other: MyLystSol[S]): MyLystSol[S]
}


// Implements case class
case object EmptySol extends MyLystSol[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyLystSol[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[S >: Nothing](element: S): MyLystSol[S] = new ConsSol(element, EmptySol)
  override def printElements: String = ""

  override def map[V](transformer: (Nothing) => V): MyLystSol[V] = EmptySol

  override def flatmap[V](transformer: (Nothing) => MyLystSol[V]): MyLystSol[V] = EmptySol

  override def filter(predicate: (Nothing) => Boolean): MyLystSol[Nothing] = EmptySol

  override def forEach(fn: Nothing => Unit): Unit = { }

  override def sort(fn: (x: Nothing, y: Nothing) => Int): MyLystSol[Nothing] = EmptySol

  override def zipWith[T1, V](
    other: MyLystSol[T1], fn: (x: Nothing, y: T1) => V
  ): MyLystSol[V] =
  {
    if (!other.isEmpty) throw new RuntimeException("Lists not the same length")
    else EmptySol
  }

  override def fold[T1](start: T1, fn: (x: T1, y: Nothing) => T1): T1 = start

  // Concatenation
  override def ++[S >: Nothing](other: MyLystSol[S]): MyLystSol[S] = other
}


// Implements case class
case class ConsSol[+T](h: T, t: MyLystSol[T]) extends MyLystSol[T] {
  override def head: T = h
  override def tail: MyLystSol[T] = t
  override def isEmpty: Boolean = false
  override def add[S >: T](element: S): MyLystSol[S] = new ConsSol(element, this)
  override def printElements: String = {
    if (this.t.isEmpty) this.h.toString
    else {
      this.h.toString + " " + this.t.printElements
    }
  }

  override def map[V](transformer: (T) => V): MyLystSol[V] = {
    new ConsSol(transformer(h), t.map(transformer))
  }

  override def flatmap[V](transformer: (T) => MyLystSol[V]): MyLystSol[V] = {
    transformer(h) ++ t.flatmap(transformer)
  }

  override def filter(predicate: T => Boolean): MyLystSol[T] = {
    if (predicate(h)) new ConsSol(h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def forEach(fn: (T) => Unit): Unit = {
    fn(this.h)
    this.t.forEach(fn)
  }

  override def sort(fn: (T, T) => Int): MyLystSol[T] = {
    def insert(x: T, sortedList: MyLystSol[T]): MyLystSol[T] = {
      if (sortedList.isEmpty) new ConsSol(x, EmptySol)
      else if (fn(x, sortedList.head) <= 0) new ConsSol(x, sortedList)
      else new ConsSol(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = this.t.sort(fn)
    insert(this.h, sortedTail)
  }

  override def zipWith[T1, V](
     other: MyLystSol[T1], fn: (x: T, y: T1) => V
  ): MyLystSol[V] =
  {
    if (other.isEmpty) throw new RuntimeException("Lists not the same length")
    else new ConsSol(fn(this.h, other.head), this.t.zipWith(other.tail, fn))
  }

  override def fold[T1](start: T1, fn: (x: T1, y: T) => T1): T1 = {
    this.t.fold(fn(start, this.h), fn)
  }

  // Concatenation
  override def ++[S >: T](other: MyLystSol[S]): MyLystSol[S] = {
    new ConsSol(h, t ++ other)
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


object TestMyLystSol extends App {
  val listInt: MyLystSol[Int] = new ConsSol(2, new ConsSol(1, EmptySol))
//  println(listInt.tail)
  val listInt1: MyLystSol[Int] = listInt.add(100)
  val listInt2: MyLystSol[Int] = listInt.add(300)
  val listInt3: MyLystSol[Int] = new ConsSol(1, EmptySol)
  val listInt4: MyLystSol[Int] = EmptySol
//  println(listInt1)
//  println(listInt1.tail)

  val listStr = EmptySol
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
//    (elem: Int) => new ConsSol(elem, new ConsSol(elem * 10, EmptySol))
//  ))

  println(s"List int 1: ${listInt1}")
  println(s"List int 2: ${listInt2}")
//  listInt1.forEach(x => println(x))

  val sortedListInt1 = listInt1.sort((x: Int, y: Int) => x - y)
  println(sortedListInt1)
  val sortedListInt3 = listInt3.sort((x: Int, y: Int) => x - y)
  println(sortedListInt3)
  val sortedListInt4 = listInt4.sort((x: Int, y: Int) => x - y)
  println(sortedListInt4)

  println(listInt1.zipWith(listInt2, (x: Int, y: Int) => x + y))

  println(listInt1.fold(0, (x: Int, y: Int) => x + y))
  println(listInt3.fold(0, (x: Int, y: Int) => x + y))
  println(listInt4.fold(0, (x: Int, y: Int) => x + y))

  // See why we need [+T] in signature of ConsSol
//  val listAnimals: ConsSol[Animal] = new ConsSol[Dog](new Dog, EmptySol)
//  println(listAnimals)
}
