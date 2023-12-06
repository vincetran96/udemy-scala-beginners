package lectures.part2oop

import playground.{
  CellPhone => CP, DSA
}

import java.util.{Date => UtilDate}
import java.sql.{Date => SQLDate}
import java.lang

object PackagingAndImports extends App {
  val writer = Writer("John", "Doe", 1990)
  writer.fullName()

  // Packages are in hierarchy matching directory structure

  // Package object (rarely used in practice)
  sayHello()

  val phone = CP()

  // To avoid ambiguous importing (same name)
  // 1. Use fully qualified names
  // 2. Use aliasing
  val d = java.util.Date()

  // Default imports
  // java.lang: String, Object, Exception
  // scala: Int, Nothing, Function
  // scala.Predef: println, ???
}
