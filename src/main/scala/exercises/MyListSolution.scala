package exercises

import scala.annotation.targetName

abstract class MyListSolution[+T] {
  def head: T
  def tail: MyListSolution[T]
  def isEmpty: Boolean
  def add[S >: T](element: S): MyListSolution[S]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[V](transformer: MyTransformer[T, V]): MyListSolution[V]
  def flatmap[V](transformer: MyTransformer[T, MyListSolution[V]]): MyListSolution[V]
  def filter(predicate: MyPredicate[T]): MyListSolution[T]

  // Concatenation
  def ++[S >: T](list: MyListSolution[S]): MyListSolution[S]
}

object Empty extends MyListSolution[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListSolution[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[S >: Nothing](element: S): MyListSolution[S] = new Cons(element, Empty)
  def printElements: String = ""

  def map[S](transformer: MyTransformer[Nothing, S]): MyListSolution[S] = Empty

  def flatmap[S](transformer: MyTransformer[Nothing, MyListSolution[S]]): MyListSolution[S] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyListSolution[Nothing] = Empty

  // Concatenation
  def ++[S >: Nothing](list: MyListSolution[S]): MyListSolution[S] = list
}

class Cons[+T](h: T, t: MyListSolution[T]) extends MyListSolution[T] {
  def head: T = h
  def tail: MyListSolution[T] = t
  def isEmpty: Boolean = false
  def add[S >: T](element: S): MyListSolution[S] = new Cons(element, this)
  def printElements: String = {
    if (this.t.isEmpty) this.h.toString
    else {
      this.h.toString + " " + this.t.printElements
    }
  }

  def map[V](transformer: MyTransformer[T, V]): MyListSolution[V] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  def flatmap[V](transformer: MyTransformer[T, MyListSolution[V]]): MyListSolution[V] = {
    transformer.transform(h) ++ t.flatmap(transformer)
  }

  def filter(predicate: MyPredicate[T]): MyListSolution[T] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  // Concatenation
  def ++[S >: T](list: MyListSolution[S]): MyListSolution[S] = {
    new Cons(h, t ++ list)
  }
}


// Must use [-T] here due to some arcane Scala reason
trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-T, V] {
  def transform(elem: T): V
}


class Animal


class Dog extends Animal


object TestMyList extends App {
  val listInt: MyListSolution[Int] = new Cons(1, new Cons(2, Empty))
  println(listInt.tail)
  val listInt1: MyListSolution[Int] = listInt.add(3)
  println(listInt1)
  println(listInt1.tail)

  val listStr = Empty
  val listStr1 = listStr.add("hello")
  val listStr2 = listStr1.add("hi")

  println(listInt1.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int) = elem * 10
  }))

  println(listInt1.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem > 2
  }))

  println(listInt1.++(listInt))

  println(listInt1.flatmap(new MyTransformer[Int, MyListSolution[Int]] {
    override def transform(elem: Int): MyListSolution[Int] = {
      new Cons(elem, new Cons(elem * 10, Empty))
    }
  }))

  // See why we need [+T] in signature of Cons
  val listAnimals: Cons[Animal] = new Cons[Dog](new Dog, Empty)
  println(listAnimals)
}
