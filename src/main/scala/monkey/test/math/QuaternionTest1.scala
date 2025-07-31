package monkey.test.math

import com.jme3.math.Quaternion
import monkey.util.extension.math.MathExt

object QuaternionTest1 extends MathExt:

  def main(args: Array[String]) =
    val q = Quaternion()
    printRotationColumns(q)
    q.fromAngles(-90.toRadians, 0, 0)
    printRotationColumns(q)

  def printRotationColumns(q: Quaternion) =
    println(q.getRotationColumn(0).str)
    println(q.getRotationColumn(1).str)
    println(q.getRotationColumn(2).str)
