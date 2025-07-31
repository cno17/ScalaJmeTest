package monkey.test.math

import com.jme3.math.Matrix3f
import monkey.util.extension.math.MathExt

object Matrix3Test extends MathExt:

  def main(args: Array[String]) =
    val m3 = Matrix3f().toRandom(-10, 10)
    m3.getRow(0)
    val v4 = V4()
    val m4 = Matrix3f().toRandom(-10, 10)
    // m4.getRow(0, v4)
