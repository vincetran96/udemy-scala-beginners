package lectures.part3fp

import scala.util.{Failure, Success, Try, Random}

object HandlingFailure extends App {
  // Create success & failure manually
  val success = Success(3)
  val failure = Failure(new RuntimeException("Failure"))

  def unsafeMethod(): String = throw new RuntimeException("No string")
  val potentialFail = Try(unsafeMethod())
  println(potentialFail)

  // Syntax sugar
  val anotherFail = Try {
    unsafeMethod()
  }
  println(anotherFail)

  // orElse chaining
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  // As API designers
  // Design safe & unsafe APIs using Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback.isSuccess)

  // Map, flatMap, filter
  val mapped = success.map(x => x * 2)
  val flatMapped = success.flatMap(x => Try(x * 2))
  val filtered = success.filter(_ > 10)
  println(mapped)
  println(flatMapped)

  // Exercises
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(html: String) = println(html)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("The socket was taken")
    }
  }

  // If html retrieved from Connection, use renderHTML to print
  val safeConnection = Try(HttpService.getConnection(hostname, port))
  val safeHTML = safeConnection.flatMap(conn => Try(conn.get("url")))
  renderHTML(safeHTML.getOrElse("Something is wrong with connection"))
}
