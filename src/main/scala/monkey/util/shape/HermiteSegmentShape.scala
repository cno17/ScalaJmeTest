package monkey.util.shape

import com.jme3.math.ColorRGBA
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import monkey.util.material.SimpleMaterial
import monkey.util.math.curve.HermiteSegment

import scala.collection.mutable.ArrayBuffer

class HermiteSegmentShape(seg: HermiteSegment, num: Int, mat: SimpleMaterial) extends Shape:

  init()

  def init() =
    positionA = ArrayBuffer[Vector3f]()
    colorA = ArrayBuffer[ColorRGBA]()
    indexA = ArrayBuffer[Int]()
    val dt = 1f / num
    for i <- 0 to num do
      val t = i * dt
      val cu = seg.curvatureAt(t)
      positionA += seg.positionAt(t)
      colorA += C(cu, cu, cu)
      indexA += i
    addChild(Geometry(createMesh(Mesh.Mode.LineStrip), mat))
