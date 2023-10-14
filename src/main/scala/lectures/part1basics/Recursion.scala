package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n, need factorial of " + (n-1))
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")
      result
    }
  }
  println(factorial(5))

  def factorial1(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, acc: BigInt): BigInt = {
      if (x <= 1) acc
      else factorialHelper(x - 1, x * acc)
    }
    factorialHelper(n, 1)
  }
  println(factorial1(5000))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION

  // The following functions implements tail recursion
  def concatNTimes(s: String, n: Int): String = {
    def concatHelper(s: String, i: Int, sAcc: String): String = {
      if (i == 0) sAcc
      else {
        val concatStr: String = if (i == 1) s else s + "&"
        concatHelper(s, i - 1, sAcc + concatStr)
      }
    }
    concatHelper(s, n, "")
  }
  println(concatNTimes("hello", 5))

  def checkPrime(n: Int): Boolean = {
    def primeHelper(x: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (x == 1) true
      else {
        primeHelper(x - 1, n % x != 0)
      }
    }
    primeHelper(n / 2, true)
  }
  println(checkPrime(15383))
  println(checkPrime(15384))

  def fibonacci(n: Int): BigInt = {
    def fiboHelper(i: Int, f0: BigInt, f1: BigInt): BigInt = {
      if (i == n) f0
      else fiboHelper(i + 1, f1, f0 + f1)
    }
    fiboHelper(1, 1, 1)
  }
  // fibonacci(1)
  // fiboHelper(1, 1, 1)

  // fibonacci(2)
  // fiboHelper(1, 1, 1)
  // fiboHelper(2, 1, 2)

  // fibonacci(3)
  // fiboHelper(1, 1, 1)
  // fiboHelper(2, 1, 2)
  // fiboHelper(3, 2, 3)

  println(fibonacci(62))
}
