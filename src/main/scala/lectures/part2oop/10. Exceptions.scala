package lectures.part2oop

object Exceptions extends App {
  val x: String = null
//  println(x.length)  // This will crash the program

  // Throw exceptions
//  val nullPointerExc = throw new NullPointerException
//  println(nullPointerExc.getClass)

  // Throwable classes extend the `Throwable` class
  // Exceptions and Errors are the major Throwable subclasses
  //  Exceptions: come from program
  //  Errors: come from the system (stackOverflowError)

  // Catch exceptions
  def doMath(n: Int): Int = {
    if (n > 100) throw new RuntimeException("n too big")
    if (n == 0) throw new RuntimeException("???")
    else 100 - n
  }

  val result = try {
    doMath(200)
  } catch {
    case
      exc: RuntimeException => println("Caught runtime exception")
  } finally {
    println(s"Finally done")
  }

  def hello(s: String, n: Int = 0): String = {
    if (n == Long.MaxValue) s
    else {
      s + hello(s, n + 1)
    }
  }

  // OutOfMemoryError
//  val array = Array.ofDim[Int](Int.MaxValue)

  // StackOverflowError
//  println(hello("HELLO"))


  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException


  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y

      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      // Probably not necessary
      else {
        val result = x / y

        if (x > 0 && y > 0 && result < 0) throw new OverflowException
        else if (x < 0 && y < 0 && result < 0) throw new OverflowException
        else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
        else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
        else result
      }
    }
  }

  println(Int.MinValue - 1000)
  println(PocketCalculator.add(10, (Int.MinValue - 1000)))
  println(PocketCalculator.divide(5, 0))
}
