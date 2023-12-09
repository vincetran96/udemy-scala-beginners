package playground

// A short review of HOF lecture
object HOFReview extends App{
  val plusOne: (Int) => Int = (x: Int) => x + 1

  val concatTwo: (String, String) => String = {
    (a: String, b: String) => a + b
  }
  println(concatTwo("John", "Doe"))

  val multiplyBy: (Int) => ((Int) => Int) = {
    (i: Int) => ((n: Int) => i * n)
  }
  val multiplyByTen = multiplyBy(10)
  println(multiplyByTen(5))

  // A function that applies another function N times
  def nTimes(fn: Int => Int, n: Int, x: Int): Int = {
    if (n <= 1) fn(x)
    else nTimes(fn, n - 1, fn(x))
  }
  println(nTimes((x) => x + 1, 10, 5))

  def nTimesBetter(fn: (Int) => Int, n: Int): (Int) => Int = {
    if (n <= 1) (x) => {
      println(s"Final call stack with n = $n")
      fn(x)
    }
    else (x) => {
      println(s"Call stack with n = $n")
      fn(nTimesBetter(fn, n - 1)(x))
    }
  }
  val plusFive = nTimesBetter((i) => i + 1, 5)
  println(plusFive(5))

}
