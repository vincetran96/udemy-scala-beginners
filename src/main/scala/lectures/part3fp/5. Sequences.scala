package lectures.part3fp

import scala.util.Random


object Sequences extends App {
  // Sequences (immutable)
  val intSeq: Seq[Int] = Seq(1, 5, 4)
  println(intSeq)
  println(intSeq.reverse)
  println(intSeq.sorted)
  println(intSeq(0))
  println(intSeq ++ (1 to 5))
  println(intSeq.sorted)

  // Ranges
  val intRange: Range = Range(1, 10)
  intRange.foreach(x => println(s"Hello $x"))
  val intRange1: Range = 1 to 10
  intRange1.foreach(x => println(s"Another range $x"))

  // Lists (immutable, linear)
  val intList: List[Int] = List(1, 2, 3)
  val prependedIntList: List[Int] = 4 :: intList
  val appendPrependIntList: List[Int] = 27 +: intList :+ 5
  println(prependedIntList)
  println(appendPrependIntList)

  val applesX5: List[String] = List.fill(5)("apple")
  println(applesX5)
  val joinedIntString: String = intList.mkString(",")
  println(joinedIntString)

  // Arrays (mutable, indexed)
  val intArr: Array[Int] = Array(1, 2, 3)
  val defaultArr = Array.ofDim[String](5)
  defaultArr.foreach(println)

  intArr(2) = 500
  println(intArr.mkString(","))

  // Vectors (immutable, indexed)
  val intVector: Vector[Int] = Vector(1, 2, 3)

  // Benchmarking Lists (immutable, linear) vs Vectors (immutable, indexed)
  val maxRuns: Int = 1000
  val maxCapacity: Int = 1000000

  def getIdxWriteTime(collection: Seq[Int]): Double = {
    val rand = new Random()
    val times = for {
      i <- Range(0, maxRuns)
    } yield {
      val startTime = System.nanoTime()
      collection.updated(rand.nextInt(maxCapacity), 0)
      System.nanoTime() - startTime
    }
    times.sum * 1.0 / maxRuns
  }

  /*
  - List:
    (+) Keeps reference to head/tail
    (-) Changing value in the middle takes a long time
  - Vector:
    (+) Depth of tree (internal data structure) is small
    (-) Needs to replace an entire 32-element chunk
   */
  val benchList: List[Int] = Range(0, maxCapacity).toList
  val benchVector: Vector[Int] = Range(0, maxCapacity).toVector
  val avgTimeList: Double = getIdxWriteTime(benchList)
  val avgTimeVector: Double = getIdxWriteTime(benchVector)
  println(avgTimeList)
  println(avgTimeVector)
}
