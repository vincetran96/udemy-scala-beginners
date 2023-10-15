package exercises

abstract class MyList {
  class Node(val data: Int) {
    var next: Option[Node] = None
  }

  var head: Option[Node]
  var tail: Option[Node]
  def isEmpty: Boolean
  def add(data: Int): Unit
  def display: String
}

class SinglyLinkedList extends MyList {
  var head: Option[Node] = None
  var tail: Option[Node] = None

  def isEmpty: Boolean = this.head.isEmpty

  def add(data: Int): Unit = {
    val node = Some(new Node(data))
    if (this.isEmpty) {
      this.head = node
      this.tail = node
    }
    else {
      this.tail.get.next = node
      this.tail = node
    }
  }

  def display: String = {
    def disp(node: Option[Node], final_str: String = ""): String = {
      if (node.isEmpty) final_str
      else {
        disp(node.get.next, final_str + ", " + node.get.data.toString)
      }
    }
    "[" + disp(this.head).stripPrefix(", ") + "]"
  }
}

class NodeTest(val data: Int) {
  var next: Option[NodeTest] = None
}

object MyList extends App {
  val n = new NodeTest(10)
  println(n.data)
  println(s"n.next=${n.next}")
  n.next = Some(new NodeTest(20))
  println(s"n.next=${n.next.get.data}")

  val sl = new SinglyLinkedList
  println(s"sl display=${sl.display}")
  sl.add(1)
  println(s"sl display=${sl.display}")
  sl.add(2)
  println(s"sl display=${sl.display}")
  sl.add(3)
  println(s"sl display=${sl.display}")
}
