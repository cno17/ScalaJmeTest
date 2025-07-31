package monkey.test.scala

import com.jme3.math.Vector2f

object FactoryMethodTest:

  def Vector2f() =
    println("a")
    new Vector2f()

  def Vector2f(x: Float, y: Float) =
    println("b")
    new Vector2f(x, y)

  def main(args: Array[String]) =
    val v = Vector2f()
    val w = Vector2f(2, 3)
