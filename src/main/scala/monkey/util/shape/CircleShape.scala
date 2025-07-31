package monkey.util.shape

import com.jme3.math.FastMath
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import monkey.util.material.SimpleMaterial

import scala.collection.mutable.ArrayBuffer

class CircleShape(rad: Float, num: Int, mat: SimpleMaterial) extends Shape:

  init()

  def init() =
    positionA = ArrayBuffer[Vector3f]()
    // colorA = ArrayBuffer[ColorRGBA]()
    indexA = ArrayBuffer[Int]()
    for i <- 0 to num do
      val a = i * FastMath.TWO_PI / num
      val sa = FastMath.sin(a)
      val ca = FastMath.cos(a)
      positionA += V(rad * ca, rad * sa, 0)
      indexA += i
    addChild(Geometry(createMesh(Mesh.Mode.LineStrip), mat))
