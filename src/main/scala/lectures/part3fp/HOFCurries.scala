package lectures.part3fp

object HOFCurries extends App {
  // A function that applies another function N times on a value
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  // Another version
  def nTimesV1(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesV1(f, n - 1)(f(x))
  }

  val plusOne = (x: Int) => x + 1
}
