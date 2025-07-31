package monkey.util.shape

import com.jme3.math.ColorRGBA
import com.jme3.math.FastMath
import com.jme3.math.Matrix3f
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import monkey.util.material.SimpleMaterial
import monkey.util.math.curve.Curve
import monkey.util.math.curve.HermiteSegment

import scala.collection.mutable.ArrayBuffer

class TubeShape(curve: Curve, radius: Float, slices: Int, stacks: Int, mat: SimpleMaterial) extends Shape:

  init()

  def init() =
    positionA = ArrayBuffer[Vector3f]()
    indexA = ArrayBuffer[Int]()
    val minT = curve.minT
    val maxT = curve.maxT
    val dt = (maxT - minT) / slices
    val da = FastMath.TWO_PI / stacks
    val rot = Matrix3f()
    for i <- 0 to slices do
      val t = minT + i * dt
      val pos = curve.positionAt(t)
      val tan = curve.tangentAt(t)
      val nor = curve.normalAt(t).multLocal(radius)
      for j <- 0 to stacks do
        val a = j * da
        rot.fromAngleAxis(a, tan)
        positionA += pos.add(rot.mult(nor))
      indexA += i
    addChild(Geometry(createMesh(Mesh.Mode.Points), mat))
