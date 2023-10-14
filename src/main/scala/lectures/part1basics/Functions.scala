package lectures.part1basics

object Functions extends App {
  def aFunction(s: String, i: Int): String = {
    s + " " + i.toString()
  }

  println(aFunction("yada", 10))

  def aParamLessFunc(): Int = 42

  println(aParamLessFunc())

  def aRepeatedFunc(s: String, n: Int): String = {
    if (n == 1) s
    else {
      s + " " + aRepeatedFunc(s, n - 1)
    }
  }

  println(aRepeatedFunc("hello", 5))

  // WHEN YOU NEED LOOPS, USE RECURSION

  def aSideEffectFunc(s: String): Unit = println(s)

  def aBigFunc(n: Int): Int = {
    def aSmallFunc(): Int = {
      val start: Int = 10
      start * n
    }

    aSmallFunc()
  }

  var aVar = aBigFunc(5);
  println(s"var=$aVar")

  def greet(name: String, age: Int): String = s"name=$name, age=$age"

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else {
      n * factorial(n - 1)
    }
  }

  val fact_0: Int = factorial(0);
  val fact_3: Int = factorial(3)
  println(s"factorial 0 =$fact_0");
  println(s"factorial 3 =$fact_3")

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else {
      fibonacci(n - 1) + fibonacci(n - 2)
    }
  }

//  val fibo_1: Int = fibonacci(1)
//  val fibo_2: Int = fibonacci(2)
//  val fibo_8: Int = fibonacci(8)
//  println(s"fibo 1=$fibo_1");
//  println(s"fibo 2=$fibo_2");
//  println(s"fibo 8=$fibo_8")
  println(fibonacci(62))

  def checkPrime(n: Int): Boolean = {
    def check(div: Int): Boolean = {
      if (div == n) true
      else if (n % div == 0) false
      else {
        check(div + 1)
      }
    }
    check(2)
  }
  val isPrime7: Boolean = checkPrime(7)
  val isPrime1337: Boolean = checkPrime(1337)
  val isPrime128: Boolean = checkPrime(128)
  println(s"is 7 prime=$isPrime7")
  println(s"is 1337 prime=$isPrime1337")
  println(s"is 128 prime=$isPrime128")

  // The lesson's implementation
  def checkPrime1(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t == 1) true
      else {
        n % t != 0 && isPrimeUntil(t - 1)
      }
    }
    isPrimeUntil(n / 2)
  }
  println(checkPrime1(7))
  println(checkPrime1(1337))
  println(checkPrime1(128))
}