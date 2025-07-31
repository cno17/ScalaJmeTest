package monkey.util.extension.math

import com.jme3.math.FastMath
import com.jme3.math.Vector2f
import com.jme3.math.Vector3f
import com.jme3.math.Vector4f

trait VectorExt:

  private val Pi = FastMath.PI
  
  def V2() = Vector2f()
  def V3() = Vector3f()
  def V4() = Vector4f()
  
  def V(x: Float, y: Float) = Vector2f(x, y)
  def V(x: Float, y: Float, z: Float) = Vector3f(x, y, z)
  def V(x: Float, y: Float, z: Float, w: Float) = Vector4f(x, y, z, w)

  extension (v: Vector2f)
    def +(w: Vector2f) = v.add(w)
    def +=(w: Vector2f) = v.addLocal(w)
    def -(w: Vector2f) = v.subtract(w)
    def -=(w: Vector2f) = v.subtractLocal(w)
    def *(s: Float) = v.mult(s)
    def *=(s: Float) = v.multLocal(s)
    def /(s: Float) = v.divide(s)
    def /=(s: Float) = v.divideLocal(s)
    // fma(s, v)
    def addLocal(w: Vector2f, s: Float) = v.addLocal(w.x * s, w.y * s)
    def subtractLocal(w: Vector2f, s: Float) = v.subtractLocal(w.x * s, w.y * s)
    def det(w: Vector2f) = v.x * w.y - w.x * v.y
    def angle(w: Vector2f) = v.angleBetween(w)
    def str = f"(${v.x}% .2f ${v.y}% .2f)"


  extension (v: Vector3f)
    def +(w: Vector3f) = v.add(w)
    def +=(w: Vector3f) = v.addLocal(w)
    def -(w: Vector3f) = v.subtract(w)
    def -=(w: Vector3f) = v.subtractLocal(w)
    def *(s: Float) = v.mult(s)
    def *=(s: Float) = v.multLocal(s)
    def /(s: Float) = v.divide(s)
    def /=(s: Float) = v.divideLocal(s)
    def addLocal(w: Vector3f, s: Float) = v.addLocal(w.x * s, w.y * s, w.z * s)
    def subtractLocal(w: Vector3f, s: Float) = v.subtractLocal(w.x * s, w.y * s, w.z * s)
    def angle(w: Vector3f) = v.angleBetween(w)
    def str = f"(${v.x}% .2f ${v.y}% .2f ${v.z}% .2f)"

  extension (v: Vector4f)
    def +(w: Vector4f) = v.add(w)
    def +=(w: Vector4f) = v.addLocal(w)
    def -(w: Vector4f) = v.subtract(w)
    def -=(w: Vector4f) = v.subtractLocal(w)
    def *(s: Float) = v.mult(s)
    def *=(s: Float) = v.multLocal(s)
    def /(s: Float) = v.divide(s)
    def /=(s: Float) = v.divideLocal(s)
    def addLocal(w: Vector4f, s: Float) = v.addLocal(w.x * s, w.y * s, w.z * s, w.w * s)
    def subtractLocal(w: Vector4f, s: Float) = v.subtractLocal(w.x * s, w.y * s, w.z * s, w.w * s)
    def angle(w: Vector4f) = v.angleBetween(w)
    def str = f"(${v.x}% .2f ${v.y}% .2f ${v.z}% .2f ${v.w}% .2f)"
