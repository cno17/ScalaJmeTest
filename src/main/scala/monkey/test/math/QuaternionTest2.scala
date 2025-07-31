package monkey.test.math

import com.jme3.math.FastMath
import com.jme3.math.Quaternion
import com.jme3.math.Vector3f
import monkey.util.extension.math.MathExt

object QuaternionTest2 extends MathExt:

  def main(args: Array[String]) =
    rotationOperatorTest()

  def rotateTest() =
    val q = Quaternion().fromAngleAxis(FastMath.HALF_PI, 1, 0, 0)
    val v = Vector3f(0, 1, 0)
    val w = q.mult(v)
    println(v)
    println(w)
    println(q.length())

  def angleAxisTest() =
    val angle1 = 0.7f
    val axis1 = V(2, 3, 5).normalizeLocal()
    val q = Quaternion().fromAngleAxis(angle1, axis1)
    val axis2 = V(0, 0, 0)
    val angle2 = q.toAngleAxis(axis2)
    println(q)
    println(s"$angle1 = $angle2")
    println(s"$axis1 = $axis2")

  def rotationOperatorTest() =
    val q = Quaternion()
    q.fromAngles(90.toRadians, 0, 0)
    val dir = V(0, 0, 1)
    println(q.mult(dir).str)
