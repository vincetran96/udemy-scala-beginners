package lectures.part3fp

import scala.util.Random


object Options extends App {
  // Some() should always have a valid value inside
  val firstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(firstOption)
  println(noOption)

  // Unsafe APIs
  // Option helps us avoiding doing Null checks ourselves
  def unsafeMethod(): String = null
  //  val result = Some(null) // Wrong
  val result = Option(unsafeMethod()) // Some or None depending on result of func call

  // As API consumers:
  // Chained methods between safe & unsafe APIs
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  // As API designers
  // Design safe & unsafe APIs using Option
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on options
  println(firstOption.isEmpty)
  println(firstOption.get) // Unsafe - will raise NoSuchElement if empty

  // Map, flatMap, filter
  println(firstOption.map(x => x * 2))
  println(firstOption.filter(x => x > 10))
  println(firstOption.flatMap(x => Option(x * -10)))

  // For comprehension

  // Exercises
  val goodConfig: Map[String, String] = Map(
    "host" -> "10.0.0.1",
    "port" -> "80"
  )
  val badConfig: Map[String, String] = Map(
    "host" -> null,
    "port" -> "80"
  )

  class Connection {
    def connect: String = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())
    println(s"The time is ${System.nanoTime()}")

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  // Try to establish connection
  // with uncertainty from config
  val config = badConfig
  val host = config.get("host")
  val port = config.get("port")

  /*
    if (h != null)
      if (p != null)
        return Connection(h, p)
    return null
   */
  val conn = host.flatMap(h => port.flatMap(p => Connection(h, p)))

  /*
    if (c != null)
      return c.connect
    return null
   */
  val connStat = conn.map(c => c.connect)
  println(conn)
  println(connStat)
}
