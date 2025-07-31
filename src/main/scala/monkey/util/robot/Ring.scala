package monkey.util.robot

import com.jme3.material.Material
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Mesh
import com.jme3.scene.Node
import com.jme3.scene.VertexBuffer
import com.jme3.util.BufferUtils
import monkey.util.extension.MonkeyExt

import scala.collection.mutable.ArrayBuffer

class Ring(mat: Material) extends Node, MonkeyExt:

  var rad1 = 4f
  var rad2 = 8f
  var radZ = 1f
  var numS = 12

  var geomTop: Geometry = null

  // init()

  def init() =
    geomTop = new Geometry("Top", topMesh())
    geomTop.setMaterial(mat)
    attachChild(geomTop)

  def topMesh() =
    val ps = ArrayBuffer[Vector3f]()
    val is = ArrayBuffer[Int]()
    for k <- 0 to numS - 1 do
      val ang = k * (2 * Math.PI / numS)
      val sin = Math.sin(ang).toFloat
      val cos = Math.cos(ang).toFloat
      ps += V(rad1 * cos, rad1 * sin, 0)
      ps += V(rad2 * cos, rad2 * sin, 0)
      if k % 2 == 0 then
        is += (2 * k)
        is += (2 * k + 1)
        is += (2 * k + 2) % (2 * numS)
        is += (2 * k + 2) % (2 * numS)
        is += (2 * k + 1)
        is += (2 * k + 3) % (2 * numS)
      else
        is += (2 * k)
        is += (2 * k + 1)
        is += (2 * k + 3) % (2 * numS)
        is += (2 * k + 3) % (2 * numS)
        is += (2 * k + 2) % (2 * numS)
        is += (2 * k)
    val pb = BufferUtils.createFloatBuffer(ps.toArray*)
    val ib = BufferUtils.createIntBuffer(is.toArray*)
    val mesh = Mesh()
    mesh.setMode(Mesh.Mode.Triangles)
    mesh.setBuffer(VertexBuffer.Type.Position, 3, pb)
    // mesh.setBuffer(VertexBuffer.Type.Normal, 3, nb)
    mesh.setBuffer(VertexBuffer.Type.Index, 1, ib)
    mesh

  override def clone() = this
