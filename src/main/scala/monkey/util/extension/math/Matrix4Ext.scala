package monkey.util.extension.math

import com.jme3.math.*
import monkey.util.math.AngleAxis
import monkey.util.math.Frame
import monkey.util.math.Frustum
import monkey.util.math.Rnd

import java.nio.FloatBuffer

trait Matrix4Ext extends Rnd, VectorExt:

  private val Pi = FastMath.PI
  
  def M4() = Matrix4f()

  extension (m: Matrix4f)

    def multPos(pos: Vector3f, res: Vector3f) = m.mult(pos, res)
    def multDir(dir: Vector3f, res: Vector3f) = m.multNormal(dir, res)

    def *(n: Matrix4f) = m.mult(n)
    def *=(n: Matrix4f) = m.multLocal(n)
    def *(v: Vector4f) = m.mult(v)

    def foreach(f: (i: Int, j: Int) => Unit) =
      for i <- 0 to 3 do
        for j <- 0 to 3 do
          f(i, j)
      m

    def setColumn(i: Int, m0: Float, m1: Float, m2: Float, m3: Float) =
      if i == 0 then {m.m00 = m0; m.m10 = m1; m.m20 = m2; m.m30 = m3}
      if i == 1 then {m.m01 = m0; m.m11 = m1; m.m21 = m2; m.m31 = m3}
      if i == 2 then {m.m02 = m0; m.m12 = m1; m.m22 = m2; m.m32 = m3}
      if i == 3 then {m.m03 = m0; m.m13 = m1; m.m23 = m2; m.m33 = m3}
      m

    def setDiagonal(m00: Float, m11: Float, m22: Float, m33: Float) =
      m.m00 = m00
      m.m11 = m11
      m.m22 = m22
      m.m33 = m33
      m

    def adjoin() = m.adjoint()
    def adjoin(res: Matrix4f) = m.adjoint(res)

    def from(b: FloatBuffer, columnMajor: Boolean) = 0

    def from(f: Frame) =
      val x = f.dirX
      val y = f.dirY
      val z = f.dirZ
      val o = f.org
      m.m00 = x.x; m.m01 = y.x; m.m02 = z.x; m.m03 = o.x
      m.m10 = x.y; m.m11 = y.y; m.m12 = z.y; m.m13 = o.y
      m.m20 = x.z; m.m21 = y.z; m.m22 = z.z; m.m23 = o.z
      m.m30 = 0.0; m.m31 = 0.0; m.m32 = 0.0; m.m33 = 1.0
      m

    // def to(res: Array[Float], columnMajor: Boolean) = 0
    def to(res: FloatBuffer, columnMajor: Boolean) = 0

    def to(res: Frame) =
      res.dirX.set(m.m00, m.m10, m.m20)
      res.dirY.set(m.m01, m.m11, m.m21)
      res.dirZ.set(m.m02, m.m12, m.m22)
      res.org.set(m.m03, m.m13, m.m23)
      res

    def toZero() = m.zero()
    def toOne() = {m.loadIdentity(); m}
    def toRandom(min: Float, max: Float) = m.foreach((i, j) => m.set(i, j, rndF(min, max)))

    def toScale(s: Vector3f): Matrix4f = 
      toScale(s.x, s.y, s.z)
    
    def toScale(sx: Float, sy: Float, sz: Float) =
      m.toOne()
      m.setDiagonal(sx, sy, sz, 1f)
      
    
    def toRotation(abc: Vector3f) =
      m.angleRotation(abc.multLocal(Pi / 180))
      m

    def toRotation(a: Float, b: Float, c: Float) =
      0

    def toRotation(aa: AngleAxis) =
      m.fromAngleNormalAxis(aa.angle, aa.axis)
      m

    def toTranslation(t: Vector3f): Matrix4f =
      toTranslation(t.x, t.y, t.z)

    def toTranslation(x: Float, y: Float, z: Float) =
      m.toOne()
      m.setColumn(3, x, y, z, 1)


    def toProjection(f: Frustum, parallel: Boolean) =
      m.fromFrustum(f.near, f.far, f.left, f.right, f.top, f.bottom, parallel)
      m

    def getScale() = m.toScaleVector()
    def getScale(res: Vector3f) = m.toScaleVector(res)
    def getRotationM() = m.toRotationMatrix()
    def getRotationM(res: Matrix3f) = m.toRotationMatrix(res)
    def getRotationQ() = m.toRotationQuat()
    def getRotationQ(res: Quaternion) = m.toRotationQuat(res)
    def getTranslation() = m.toTranslationVector()
    def getTranslation(res: Vector3f) = m.toTranslationVector(res)

    def transformPos(pos: Vector3f) = m.mult(pos)
    def transformPos(pos: Vector3f, res: Vector3f) = m.mult(pos, res)
    def transformDir(dir: Vector3f) = m.multNormal(dir, V3())
    def transformDir(dir: Vector3f, res: Vector3f) = m.mult(dir, res)

    def project(v: Vector3f, res: Vector3f) = m.multProj(v, res)

    def leftRotate(a: Float, b: Float, c: Float) =
      val rot = Matrix4f().toRotation(Vector3f(a, b, c))
      rot.mult(m, m)

    def rightRotate(a: Float, b: Float, c: Float) =
      val rot = Matrix4f().toRotation(Vector3f(a, b, c))
      m.mult(rot, m)

    def str =
      val s0 = f"${m.m00}% .2f ${m.m01}% .2f ${m.m02}% .2f ${m.m03}% .2f"
      val s1 = f"${m.m10}% .2f ${m.m11}% .2f ${m.m12}% .2f ${m.m13}% .2f"
      val s2 = f"${m.m20}% .2f ${m.m21}% .2f ${m.m22}% .2f ${m.m23}% .2f"
      val s3 = f"${m.m30}% .2f ${m.m31}% .2f ${m.m32}% .2f ${m.m33}% .2f"
      s"$s0\n$s1\n$s2\n$s3\n"
