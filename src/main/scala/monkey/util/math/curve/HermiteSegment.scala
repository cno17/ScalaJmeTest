package monkey.util.math.curve

import com.jme3.math.Vector3f
import monkey.util.math.Arrow

class HermiteSegment(val a0: Arrow, a1: Arrow) extends Curve:

  def pos0 = a0.pos
  def tan0 = a0.dir
  def pos1 = a1.pos
  def tan1 = a1.dir

  override def c0(t: Float, res: Vector3f) =
    val t1 = t
    val t2 = t1 * t
    val t3 = t2 * t
    val cp0 = +2 * t3 - 3 * t2 + 1
    val cp1 = -2 * t3 + 3 * t2
    val ct0 = t3 - 2 * t2 + t1
    val ct1 = t3 - t2
    set(res, cp0, ct0, cp1, ct1)

  override def c1(t: Float, res: Vector3f) =
    val t1 = t
    val t2 = t1 * t
    val cp0 = +6 * t2 - 6 * t1
    val cp1 = -6 * t2 + 6 * t1
    val ct0 = 3 * t2 - 4 * t1 + 1
    val ct1 = 3 * t2 - 2 * t1
    set(res, cp0, ct0, cp1, ct1)

  override def c2(t: Float, res: Vector3f) =
    val t1 = t
    val cp0 = +12 * t1 - 6
    val cp1 = -12 * t1 + 6
    val ct0 = 6 * t1 - 4
    val ct1 = 6 * t1 - 2
    set(res, cp0, ct0, cp1, ct1)

  override def c3(t: Float, res: Vector3f) =
    val cp0 = +12
    val cp1 = -12
    val ct0 = 6
    val ct1 = 6
    set(res, cp0, ct0, cp1, ct1)

  protected def set(res: Vector3f, cp0: Float, ct0: Float, cp1: Float, ct1: Float) =
    res.x = cp0 * pos0.x + ct0 * tan0.x + cp1 * pos1.x + ct1 * tan1.x
    res.y = cp0 * pos0.y + ct0 * tan0.y + cp1 * pos1.y + ct1 * tan1.y
    res.z = cp0 * pos0.z + ct0 * tan0.z + cp1 * pos1.z + ct1 * tan1.z
    res
