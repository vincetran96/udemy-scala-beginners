package lectures.part3fp

object TuplesAndMaps extends App {
  // Tuples
  val aTuple = Tuple2[Int, String](2, "sth") // Tuple2[Int, String] = (Int, String)
  println(aTuple._1)
  println(aTuple(0))
  println(aTuple.copy(_2 = "sth1"))

  val swapTuple = aTuple.swap
  println(swapTuple)


  // Maps (immutable)
  // With default value allows the apply() method to not raise exception
  val phoneBook: Map[String, Int] = Map(
    ("A", 1),
    ("B", 2),
    ("C" -> 3)
  ).withDefaultValue(-1)
  println(phoneBook)

  println(phoneBook.contains("B"))
  println(phoneBook.get("D"))
  println(phoneBook("D"))

  val newContact = ("E", 5)
  val phoneBook1 = phoneBook + newContact
  println(phoneBook1)

  // Remove key
  val phoneBook2 = phoneBook - "B"
  println(phoneBook2)

  // Update key
  val phoneBook3 = phoneBook.updated("B", 200)
  println(phoneBook3)

  // `map` for Map returns Map
  println(phoneBook1.map((x, y) => x.toLowerCase -> y * 10))
  println(phoneBook1.map(pair => (pair._1.toLowerCase, pair._2 * 10)))
  println(phoneBook1.view.mapValues(v => v * 100).toMap)

  println(phoneBook1.view.filterKeys(k => k.startsWith("A")).toMap)

  // Conversion to other collections
  println(phoneBook1.toList)
  val tupToMap = List(aTuple, swapTuple).toMap
  println(tupToMap)

  // GroupBy
  val transactions: Map[String, Int] = Map(
    ("AA", 1),
    ("AB", 2),
    ("BA", 3),
    ("BB", 4)
  )
  transactions.foreach(println)
  // First way
  val transGb = transactions
    .groupBy(pair => pair._1.slice(0, 1))
    .view.mapValues(m => m.foldLeft(0)((acc, item) => acc + item._2))
    .toMap
  println(transGb)
  // Second way
  val transGb1 = transactions.toList
    .groupBy(pair => pair._1.slice(0, 1))
    .view.mapValues(l => l.foldLeft(0)((acc, elem) => acc + elem._2))
    .toMap
  println(transGb1)
}


object exercises extends App {
  /** Network = Map
    - Key = username
    - Value = Set of friends
   */
  def addUser(network: Map[String, Set[String]], uid: String) = {
    network + (uid -> Set())
  }

  /**
   * Removes user from network
   */
  def removeUser(network: Map[String, Set[String]], uid: String) = {
    (network - uid).map((u, friends) => (u -> friends.filter(fr => fr != uid)))
  }

  /**
   * Makes f2 friend of f1
   */
  def friend(network: Map[String, Set[String]], uid1: String, uid2: String) = {
    val f1Friends = network(uid1) + uid2
    val f2Friends = network(uid2) + uid1
      network.updated(uid1, f1Friends).updated(uid2, f2Friends)
  }

  /**
   * Makes f2 not friend of f1
   */
  def unfriend(network: Map[String, Set[String]], uid1: String, uid2: String) = {
    val f1Friends = network(uid1) - uid2
    val f2Friends = network(uid2) - uid1
    network.updated(uid1, f1Friends).updated(uid2, f2Friends)
  }

  /**
   * Counts a person's # of friends
   */
  def countFriends(network: Map[String, Set[String]], uid: String) = {
    network(uid).size
  }

  /**
   * Person(s) with the most friends
   */
  def mostFriends(network: Map[String, Set[String]]) = {
    val maxFriends = network.maxBy(p => p._2.size)._2.size
    network.filter(p => p._2.size == maxFriends).keys
  }

  /**
   * Number of people with no friends
   */
  def numNoFriends(network: Map[String, Set[String]]) = {
    network.count(p => p._2.isEmpty)
  }

  /**
   * Whether 2 people has a connection
   * Direct: Friends with each other
   * Non-direct: Find a friend set containing both users
   */
  def isConnected(network: Map[String, Set[String]], uid1: String, uid2: String) = {
    if (network(uid1).contains(uid2)) true
    else {
      val mutualFriends = network(uid1).filter(friend => network(friend).contains(uid2))
      !mutualFriends.isEmpty
    }
  }


  // Test
  val net = Map[String, Set[String]](
    ("A", Set()),
    ("B", Set()),
    ("C", Set())
  )
  val net1 = addUser(net, "D")
  val net2 = friend(net1, "A", "B")
  val net3 = friend(net2, "A", "C")
  println(net3)
  println(countFriends(net3, "A"))
  println(mostFriends(net3))
  println(numNoFriends(net3))

  val net4 = unfriend(net3, "C", "A")
  println(net4)
  println(countFriends(net4, "A"))

  val net5 = friend(net4, "A", "D")
  val net6 = friend(net5, "C", "D")
  val net7 = addUser(net6, "E")
  println(net7)
  println(isConnected(net7, "A", "B"))
  println(isConnected(net7, "A", "C"))
  println(isConnected(net7, "A", "D"))
  println(isConnected(net7, "B", "D"))
  println(isConnected(net7, "A", "E"))
  println(mostFriends(net7))

  println(removeUser(net7, "A"))

  val emptyNet = Map[String, Set[String]]()
  val net8 = addUser(addUser(emptyNet, "Bob"), "Mary")
  println(net8)
  println(friend(net8, "Bob", "Mary"))
  println(unfriend(friend(net8, "Bob", "Mary"), "Bob", "Mary"))

}
