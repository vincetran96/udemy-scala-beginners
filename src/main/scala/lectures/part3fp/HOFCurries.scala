package lectures.part3fp

object HOFCurries extends App {
  // A function that applies another function N times on a value
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  // Another version
  def nTimesV1(fn: (Int) => Int, n: Int): (Int) => Int = {
    if (n <= 1) (x) => fn(x)
    else (x) => nTimesV1(fn, n - 1)(fn(x))
  }

  // Another version
  def nTimesV2(fn: (Int) => Int, n: Int): (Int) => Int = {
    if (n <= 1) (x) => fn(x)
    else (x) => fn(nTimesV2(fn, n - 1)(x))
  }

  val plusOne = (x: Int) => x + 1
  println(nTimesV1(plusOne, 10)(5))
  println(nTimesV2(plusOne, 10)(5))

  // Curried functions
  val superAdder = (x: Int) => ((y: Int) => x + y)
  val add10 = superAdder(10)
  println(add10(2))
  println(superAdder(20)(2))

  val superMultiplier = (x: Int, y: Int) => ((z: Int) => (x + y) * z)
  println(superMultiplier(5, 2)(3))

  // Functions with multiple parameter lists
  def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def run(x: Int)(f: Int => String): String = f(x)
  println(run(10)(x => x.toString))

  /*
  Expand MyLyst
    - forEach method
    - sort method
    - zipWith method
    - fold method
   */

  // toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
  def toCurry[T](fn: (Int, Int) => T): (Int => Int => T) = {
    x => (y => fn(x, y))
  }
  val toCurrySum = toCurry((x, y) => x + y)
  val toCurrySum10 = toCurrySum(10)
  println(toCurrySum10(1))

  // fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
  def fromCurry(fn: (Int => (Int => Int))): ((Int, Int) => Int) = {
    (x, y) => fn(x)(y)
  }
  val fromCurrySum = fromCurry(x => (y => (x + y)))
  println(fromCurrySum(1, 2))

  // compose(f, g) => x => f(g(x))
  def compose(f: Int => Int, g: Int => Int) = (x: Int) => f(g(x))
  val composePlusMultiply = compose(x => x + 1, y => y * 5)
  println(composePlusMultiply(3))

  // andThen(f, g) => x => g(f(x))
  def andThen(f: Int => Int, g: Int => Int) = (x: Int) => g(f(x))
  val andThenPlusMultiply = andThen(x => x + 1, y => y * 5)
  println(andThenPlusMultiply(3))
}
