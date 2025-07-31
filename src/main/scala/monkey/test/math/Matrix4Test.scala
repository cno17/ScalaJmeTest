package monkey.test.math

import com.jme3.math.FastMath
import com.jme3.math.Matrix4f
import monkey.util.extension.MonkeyExt
import monkey.util.extension.math.MathExt
import monkey.util.math.Frame

object Matrix4Test extends MathExt:

  def main(args: Array[String]) =
    val m = M4().toTranslation(2, 3, 5)
    println(m)


  def leftRightTest() =
    val rot = Matrix4f().toRotation(V(1.57, 0, 0))
    val tra = Matrix4f().toTranslation(2, 3, 5)
    println(tra)

  def frameTest() =
    val f = Frame()
    val m = Matrix4f().from(f)
    println(f)
    println(m)
