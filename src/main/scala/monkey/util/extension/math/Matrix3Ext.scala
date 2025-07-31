package monkey.util.extension.math

import com.jme3.math.*
import monkey.util.math.Rnd

trait Matrix3Ext extends Rnd:

  private val Pi = FastMath.PI

  def M3() = Matrix3f()
  
  extension (m: Matrix3f)

    def *(n: Matrix3f) = m.mult(n)
    def *=(n: Matrix3f) = m.multLocal(n)
    def *(v: Vector3f) = m.mult(v)

    def m00 = m.get(0, 0)
    def m01 = m.get(0, 1)
    def m02 = m.get(0, 2)
    def m10 = m.get(1, 0)
    def m11 = m.get(1, 1)
    def m12 = m.get(1, 2)
    def m20 = m.get(2, 0)
    def m21 = m.get(2, 1)
    def m22 = m.get(2, 2)

    def foreach(f: (i: Int, j: Int) => Unit) =
      for i <- 0 to 2 do
        for j <- 0 to 2 do
          f(i, j)
      m

    def toZero() = m.zero()
    def toOne() = {m.loadIdentity(); m}
    def toRandom(min: Float, max: Float) = m.foreach((i, j) => m.set(i, j, rndF(min, max)))
    def toRotationX(a: Float) = 0
    def toRotationY(a: Float) = 0
    def toRotationZ(a: Float) = 0


    def str =
      val s0 = f"${m.m00}% .2f ${m.m01}% .2f ${m.m02}% .2f"
      val s1 = f"${m.m10}% .2f ${m.m11}% .2f ${m.m12}% .2f"
      val s2 = f"${m.m20}% .2f ${m.m21}% .2f ${m.m22}% .2f"
      s"$s0\n$s1\n$s2\n"
