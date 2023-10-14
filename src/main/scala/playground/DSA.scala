package playground

object DSA extends App {
  def binSearch(vec: Vector[Int], value: Int): Int = {
    def _search(i_start: Int, i_end: Int): Int = {
      if (i_start > i_end) -1
      else {
        val i_mid = (i_start + i_end) / 2
        println(s"Searching at index $i_mid")
        if (vec(i_mid) == value) i_mid
        else if (vec(i_mid) < value) {
          _search(i_mid + 1, i_end)
        }
        else {
          _search(i_start, i_mid - 1)
        }
      }
    }
    _search(0, vec.length - 1)
  }

  println(binSearch(Vector.range(0, 100000), 99))
}
