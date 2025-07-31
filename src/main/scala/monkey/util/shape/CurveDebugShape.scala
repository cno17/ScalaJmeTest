package monkey.util.shape

import com.jme3.asset.AssetManager
import com.jme3.math.Matrix3f
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import monkey.util.material.ColorMaterial

import scala.collection.mutable.ArrayBuffer

/**
 * Composed of three meshes:
 */

class CurveDebugShape(val ps: Array[Vector3f], am: AssetManager) extends Shape:

  var ts: Array[Vector3f] = null
  var ns: Array[Vector3f] = null

  var mp: ColorMaterial = null
  var mt: ColorMaterial = null
  var mn: ColorMaterial = null

  init()

  def init() =
    ts = createTangents()
    ns = createNormals()
    mp = ColorMaterial(am)
    mp.setColor(C(0.8f, 0.8f, 0.8f))
    mt = ColorMaterial(am)
    mt.setColor(C(0.8f, 0.8f, 0.2f))
    mn = ColorMaterial(am)
    mn.setColor(C(0.2f, 0.2f, 0.8f))
    addChild(Geometry(createPositionMesh(), mp))
    addChild(Geometry(createTangentMesh(), mt))
    addChild(Geometry(createNormalMesh(), mn))

  private def n = ps.size

  private def mod(i: Int) = if i < 0 then i + n else if i > n - 1 then i - n else i

  private def createTangents() =
    val res = ArrayBuffer[Vector3f]()
    for i <- 0 to n - 1 do
      val t = ps(mod(i + 1)).subtract(ps(i)).normalizeLocal().multLocal(1f)
      res += t
    res.toArray

  private def createNormals() =
    val res = ArrayBuffer[Vector3f]()
    val rot = Matrix3f()
    for i <- 0 to n - 1 do
      val u = ts(mod(i - 1))
      val v = ts(i)
      val n = u.cross(v).normalizeLocal().multLocal(1f)
      res += n
    res.toArray

  // simplify!
  private def createPositionMesh() =
    positionA = ArrayBuffer[Vector3f]()
    indexA = ArrayBuffer[Int]()
    positionA ++= ps
    indexA ++= 0 to n - 1
    createMesh(Mesh.Mode.Points)

  // TODO DRY

  private def createTangentMesh() =
    positionA = ArrayBuffer[Vector3f]()
    indexA = ArrayBuffer[Int]()
    for i <- 0 to n - 1 do
      positionA += ps(i)
      positionA += ps(i).add(ts(i))
      indexA += 2 * i + 0
      indexA += 2 * i + 1
    createMesh(Mesh.Mode.Lines)

  private def createNormalMesh() =
    positionA = ArrayBuffer[Vector3f]()
    indexA = ArrayBuffer[Int]()
    for i <- 0 to n - 1 do
      positionA += ps(i)
      positionA += ps(i).add(ns(i))
      indexA += 2 * i + 0
      indexA += 2 * i + 1
    createMesh(Mesh.Mode.Lines)
