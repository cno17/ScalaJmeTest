package monkey.util.extension.math

import com.jme3.math.*
import com.jme3.util.TempVars
import monkey.util.math.AngleAxis
import monkey.util.math.Frame
import monkey.util.math.Frustum

import java.nio.FloatBuffer

trait QuaternionExt:

  private val Pi = FastMath.PI
  private val D2R = FastMath.DEG_TO_RAD

  def Q(x: Float, y: Float, z: Float, w: Float) = Quaternion(x, y, z, w)
  def Q(v: Vector3f, w: Float) = Quaternion(v.x, v.y, v.z, w)

  extension (q: Quaternion)

    // def x = q.getX good?

    def *(r: Quaternion) = q.mult(r)
    def *=(r: Quaternion) = q.multLocal(r)
    def *(v: Vector3f) = q.mult(v)

    def length() = q.norm()
    
    // def fromAnglesDeg(a: Float, b: Float, c: Float) = q.fromAngles(a.toRadians, b * D2R, c * D2R)

    def fromAngleAxis(a: Float, x: Float, y: Float, z: Float) =
      val ang = 0.5f * a
      val sin = FastMath.sin(ang)
      val cos = FastMath.cos(ang)
      q.set(sin * x, sin * y, sin * z, cos)


    def toOne() = {q.loadIdentity(); q}
    def toRotationX(a: Float): Quaternion = fromAngleAxis(a, 1, 0, 0)

    def toAngleAxis(res: AngleAxis) = 0

    def invert() = q.inverse()
    def invertLocal() = q.inverseLocal()

    // todo: implement is without TempVars: 
    
    def rotateX(a: Float) =
      val tv = TempVars.get()
      q.multLocal(tv.quat1.fromAngles(a, 0, 0))
      tv.release()

    def rotateY(b: Float) =
      val tv = TempVars.get()
      q.multLocal(tv.quat1.fromAngles(0, b, 0))
      tv.release()

    def rotateZ(c: Float) =
      val tv = TempVars.get()
      q.multLocal(tv.quat1.fromAngles(0, 0, c))
      tv.release()



    def str = f"(${q.getX}% .2f ${q.getY}% .2f ${q.getZ}% .2f ${q.getW}% .2f)"


// def multiply(q1: Quaternion) = q.mult(q1)
// def multiply(q1: Quaternion, res: Quaternion) = q.mult(q1, res)
// def multiplyLocal(q1: Quaternion) = q.multLocal(q1)

// def =*(q1: Quaternion, q2: Quaternion) = q.set(q1).multLocal(q2)
// def fromMatrix(rot: Matrix3f) = q.fromRotationMatrix(rot)
// def toMatrix(res: Matrix3f) = q.toRotationMatrix(res)
// def toMatrix(res: Matrix4f) = q.toRotationMatrix(res)

// def rotate(v: Vector3f) = q.mult(v)
// def rotate(v: Vector3f, res: Vector3f) = q.mult(v, res)
// def rotateLocal(v) = 0 // unlucky name
