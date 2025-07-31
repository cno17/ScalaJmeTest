package monkey.util.math.curve

import com.jme3.math.Vector3f
import monkey.util.math.Arrow

import scala.math.floor

// compute tangets: bias, tension

class HermiteCurve(as: Array[Arrow]) extends Curve:

  var segments: Array[HermiteSegment] = null

  init()

  def init() =
    val num = as.size
    minT = 0f
    maxT = (num - 1).toFloat
    segments = Array.ofDim[HermiteSegment](num - 1)
    for i <- 0 to num - 2 do segments(i) = HermiteSegment(as(i), as(i + 1))

  // DRY

  override def c0(t: Float, res: Vector3f) =
    assert(minT <= t && t <= maxT)
    val i = floor(t).toInt
    segments(i).c0(t - i, res)

  override def c1(t: Float, res: Vector3f) =
    assert(minT <= t && t <= maxT)
    val i = floor(t).toInt
    segments(i).c1(t - i, res)

  override def c2(t: Float, res: Vector3f) =
    assert(minT <= t && t <= maxT)
    val i = floor(t).toInt
    segments(i).c2(t - i, res)

  override def c3(t: Float, res: Vector3f) =
    assert(minT <= t && t <= maxT)
    val i = floor(t).toInt
    segments(i).c3(t - i, res)
