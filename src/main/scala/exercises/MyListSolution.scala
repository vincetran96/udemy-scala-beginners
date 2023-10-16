package exercises

abstract class MyListSolution {
  def head: Int
  def tail: MyListSolution
  def isEmpty: Boolean
  def add(element: Int): MyListSolution
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyListSolution {
  def head: Int = throw new NoSuchElementException
  def tail: MyListSolution = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyListSolution = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyListSolution) extends MyListSolution {
  def head: Int = h
  def tail: MyListSolution = t
  def isEmpty: Boolean = false
  def add(element: Int): MyListSolution = new Cons(element, this)
  def printElements: String = {
    if (this.t.isEmpty) this.h.toString
    else {
      this.h.toString + " " + this.t.printElements
    }
  }
}

object TestMyList extends App {
  val list = new Cons(1, new Cons(2, Empty))
  println(list.tail)
  val list1 = list.add(3)
  println(list1.toString)
}
