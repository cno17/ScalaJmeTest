package monkey.test.util

object ExtTest1:

  class A

  class B extends A

  extension (a: A)
    def += (x: Int) = println("int added")

  extension (b: B)
    def += (s: String) = println("string added")

  def main(args: Array[String]) =
    val b = B()
    b += "one"
    // b += 1
