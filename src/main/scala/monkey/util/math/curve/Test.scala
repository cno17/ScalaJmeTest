package monkey.util.math.curve

import com.jme3.math.Vector3f
import monkey.util.extension.math.VectorExt
import monkey.util.math.Arrow

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Test extends Random, VectorExt:

  def main(args: Array[String]) = curveTest()

  def segmentTest() =
    val a0 = Arrow(V(0, 0, 0), V(1, +1, 0))
    val a1 = Arrow(V(1, 0, 0), V(1, -1, 0))
    val hs = HermiteSegment(a0, a1)
    println(hs.curvatureAt(0.6f))

  def curveTest() =
    val n = 5
    val ps = ArrayBuffer[Vector3f]()
    val ts = ArrayBuffer[Vector3f]()
    for i <- 0 to n do
      ps += pos()
      ts += tan()
    // val hc = HermiteCurve(ps.toArray, ts.toArray)
    // println(hc.curvatureAt(1.0f))

  def pos() =
    val x = 10 * nextFloat()
    val y = 10 * nextFloat()
    val z = 10 * nextFloat()
    V(x, y, z)

  def tan() =
    val x = -1 + 2 * nextFloat()
    val y = -1 + 2 * nextFloat()
    val z = -1 + 2 * nextFloat()
    V(x, y, z)
