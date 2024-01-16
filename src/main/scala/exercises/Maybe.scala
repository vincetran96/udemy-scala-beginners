package exercises


abstract class Maybe[+A] {
  def head: A
  def tail: Maybe[A]
  def isEmpty: Boolean
  def printElements: String
  override def toString: String = "[" + this.printElements.trim + "]"

  def add[B >: A](elem: B): Maybe[B]
  def ++[B >: A](other: Maybe[B]): Maybe[B]

  def map[B](fn: A => B): Maybe[B]
  def flatmap[B](fn: A => Maybe[B]): Maybe[B]
  def filter(fn: A => Boolean): Maybe[A]
}


case object MaybeEmpty extends Maybe[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: Maybe[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def printElements: String = ""

  override def add[B >: Nothing](elem: B): Maybe[B] = new MaybeOne(elem, MaybeEmpty)
  override def ++[B >: Nothing](other: Maybe[B]): Maybe[B] = other

  override def map[B](fn: Nothing => B): Maybe[B] = MaybeEmpty
  override def flatmap[B](fn: Nothing => Maybe[B]): Maybe[B] = MaybeEmpty
  override def filter(fn: Nothing => Boolean): Maybe[Nothing] = MaybeEmpty
}


case class MaybeOne[+A](h: A, t: Maybe[A]) extends Maybe[A] {
  override def head: A = h
  override def tail: Maybe[A] = t
  override def isEmpty: Boolean = false
  override def printElements: String = h.toString + " " + t.printElements

  override def add[B >: A](elem: B): Maybe[B] = {
    println("Cannot add any more elements")
    this
  }
  override def ++[B >: A](other: Maybe[B]): Maybe[B] = {
    println("Cannot add any more elements")
    this
  }

  override def map[B](fn: A => B): Maybe[B] = new MaybeOne(fn(h), t.map(fn))
  override def flatmap[B](fn: A => Maybe[B]): Maybe[B] = fn(h) ++ t.flatmap(fn)
  override def filter(fn: A => Boolean): Maybe[A] = {
    if (fn(h)) new MaybeOne(h, t.filter(fn))
    else t.filter(fn)
  }
}


object TestMaybe extends App {
  val ints: Maybe[Int] = new MaybeOne(1, MaybeEmpty)
  println(ints)
  val ints1 = ints.add(2)
  println(ints1)
}