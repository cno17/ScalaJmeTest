package monkey.util.math.curve

import com.jme3.math.FastMath
import com.jme3.math.Matrix3f
import com.jme3.math.Vector3f

// TODO GC, bin = tan x nor?

abstract class Curve:

  var minT = 0f
  var maxT = 1f

  def c0(t: Float, res: Vector3f): Vector3f
  def c1(t: Float, res: Vector3f): Vector3f
  def c2(t: Float, res: Vector3f): Vector3f
  def c3(t: Float, res: Vector3f): Vector3f

  def positionAt(t: Float, res: Vector3f = Vector3f()) = c0(t, res)
  def tangentAt(t: Float, res: Vector3f = Vector3f()) = c1(t, res).normalizeLocal()
  def normalAt(t: Float, res: Vector3f = Vector3f()) = c2(t, res).normalizeLocal()
  def binormalÂt(t: Float, res: Vector3f = Vector3f()) = c3(t, res).normalizeLocal()

  def curvatureAt(t: Float) =
    val v1 = c1(t, Vector3f())
    val v2 = c2(t, Vector3f())
    v1.cross(v2).length() / FastMath.pow(v1.length(), 3)

  def torsionAt(t: Float) =
    val v1 = c1(t, Vector3f())
    val v2 = c2(t, Vector3f())
    val v3 = c3(t, Vector3f())
    val mat = Matrix3f()
    mat.setColumn(0, v1)
    mat.setColumn(1, v2)
    mat.setColumn(2, v3)
    val d12 = v1.dot(v2)
    val d22 = v2.dot(v2)
    mat.determinant() / (d12 * (d22 - d12))

  def length(a: Float = minT, b: Float = maxT, n: Int = 100) =
    val dt = (b - a) / n
    val vec = Vector3f()
    var res = (c1(a, vec).length() + c1(b, vec).length()) / 2
    for i <- 1 to n - 1 do
      res += c1(i * dt, vec).length()
    res * dt

  def timeToLength(t: Float) = 0
  def lengthToTime(t: Float) = 0
  
  def sampleByTime(n: Int): Array[Float] = null
  def sampleByLength(n: Int): Array[Float] = null
  
  protected def integral(f: Float => Float, a: Float, b: Float, n: Int) =
    val dx = (b - a) / n
    var res = (f(a) + f(b)) / 2
    for i <- 1 to n - 1 do
      res += f(i * dx)
    res * dx


