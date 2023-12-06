package lectures.part3fp

object AnonymousFunctions extends App {
  // Anonymous function (lambda)
  val doubler: Int => Int = x => x * 2

  // Multiple params
  val adder = (a: Int, b: Int) => a + b
  val adder1: (Int, Int) => Int = (a, b) => {a + b}

  // No params, must call with brackets
  val noParams = () => 3
  println(noParams)
  println(noParams())

  // Curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar
  // The _ parameter can be used for multiple params,
  // which means that if there is a function that uses a param
  // multiple times, do NOT use _
  val niceIncrementer: Int => Int = _ + 1  // same as x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _  // same as (a, b) => a + b

  // Curried function
  val multiplyBy = (i: Int) => {(n: Int) => i * n}
  val multiplyBy10 = multiplyBy(10)
  println(multiplyBy10(5))

  val someFunc = (x: Int) => x + 1
  println(someFunc(10))
}
