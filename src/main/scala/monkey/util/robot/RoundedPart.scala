package monkey.util.robot

import com.jme3.material.Material
import com.jme3.math.FastMath.*
import com.jme3.math.Vector2f
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Mesh
import com.jme3.scene.Node
import com.jme3.scene.VertexBuffer
import com.jme3.util.BufferUtils
import monkey.util.extension.MonkeyExt

import scala.collection.mutable.ArrayBuffer

/**
  * point buffer layout!
  * DRY!
  * corner bug for small radZ!
  */

class RoundedPart(rad1: Float, rad2: Float, radZ: Float, numS: Int, mat: Material) extends Node, MonkeyExt:

  attachChild(geometry(topMesh()))
  attachChild(geometry(bottomMesh()))
  attachChild(geometry(outerMesh()))
  attachChild(geometry(innerMesh()))
  
  private def angNE = angle(2 * rad1, +rad2)
  private def angSE = angle(2 * rad1, -rad2)

  private def outerScaleFor(ang: Float) =
    val sinA = sin(ang)
    val cosA = cos(ang)
    if ang <= angNE then 2 * rad1 / cosA
    else if ang <= 0.5f * PI then rad2 / sinA
    else if ang <= 1.5f * PI then rad2
    else if ang <= angSE then rad2 / abs(sinA)
    else 2 * rad1 / cosA

  private def topMesh() =
    val pa = ArrayBuffer[Vector3f]()
    val na = ArrayBuffer[Vector3f]()
    for i <- 0 to numS - 1 do
      val ang = i * 2 * PI / numS
      val sinA = sin(ang)
      val cosA = cos(ang)
      val scaF = outerScaleFor(ang)
      pa += V(rad1 * cosA, rad1 * sinA, radZ)
      pa += V(scaF * cosA, scaF * sinA, radZ)
      na += V(0, 0, 1)
      na += V(0, 0, 1)
    val ia = ArrayBuffer[Int]()
    for i <- 0 to numS - 1 do
        ia += (2 * i)
        ia += (2 * i + 1) 
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 1)
        ia += (2 * i + 3) % (2 * numS)
    alignTopPositions(pa)
    mesh(pa.toArray, na.toArray, ia.toArray)

  private def bottomMesh() =
    val pa = ArrayBuffer[Vector3f]()
    val na = ArrayBuffer[Vector3f]()
    for i <- 0 to numS - 1 do
      val ang = i * 2 * PI / numS
      val sinA = sin(ang)
      val cosA = cos(ang)
      val scaF = outerScaleFor(ang)
      pa += V(rad1 * cosA, rad1 * sinA, -radZ)
      pa += V(scaF * cosA, scaF * sinA, -radZ)
      na += V(0, 0, -1)
      na += V(0, 0, -1)
    val ia = ArrayBuffer[Int]()
    for i <- 0 to numS - 1 do
        ia += (2 * i)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 1)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 3) % (2 * numS)
        ia += (2 * i + 1)
    alignBottomPositions(pa)
    mesh(pa.toArray, na.toArray, ia.toArray)

  private def outerMesh() =
    val pa = ArrayBuffer[Vector3f]()
    val na = ArrayBuffer[Vector3f]()
    for i <- 0 to numS - 1 do
      val ang = i * 2 * PI / numS
      val sinA = sin(ang)
      val cosA = cos(ang)
      val scaF = outerScaleFor(ang)
      pa += V(scaF * cosA, scaF * sinA, +radZ)
      pa += V(scaF * cosA, scaF * sinA, -radZ)
      na += V(cosA, sinA, 0)
      na += V(cosA, sinA, 0)
    val ia = ArrayBuffer[Int]()
    for i <- 0 to numS - 1 do
        ia += (2 * i)
        ia += (2 * i + 1) % (2 * numS)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 1) % (2 * numS)
        ia += (2 * i + 3) % (2 * numS)
    alignTopPositions(pa)
    alignBottomPositions(pa)
    mesh(pa.toArray, na.toArray, ia.toArray)

  private def innerMesh() =
    val pa = ArrayBuffer[Vector3f]()
    val na = ArrayBuffer[Vector3f]()
    for i <- 0 to numS - 1 do
      val ang = i * 2 * PI / numS
      val sinA = sin(ang)
      val cosA = cos(ang)
      pa += V(rad1 * cosA, rad1 * sinA, +radZ)
      pa += V(rad1 * cosA, rad1 * sinA, -radZ)
      na += V(-cosA, -sinA, 0)
      na += V(-cosA, -sinA, 0)
    val ia = ArrayBuffer[Int]()
    for i <- 0 to numS - 1 do
        ia += (2 * i)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 1) % (2 * numS)
        ia += (2 * i + 2) % (2 * numS)
        ia += (2 * i + 3) % (2 * numS)
        ia += (2 * i + 1) % (2 * numS)
    mesh(pa.toArray, na.toArray, ia.toArray)

  private def mesh(pa: Array[Vector3f], na: Array[Vector3f], ia: Array[Int]) =
    val pb = BufferUtils.createFloatBuffer(pa.toArray*)
    val nb = BufferUtils.createFloatBuffer(na.toArray*)
    val ib = BufferUtils.createIntBuffer(ia.toArray*)
    val mesh = Mesh()
    mesh.setMode(Mesh.Mode.Triangles)
    mesh.setBuffer(VertexBuffer.Type.Position, 3, pb)
    mesh.setBuffer(VertexBuffer.Type.Normal, 3, nb)
    mesh.setBuffer(VertexBuffer.Type.Index, 1, ib)
    mesh

  private def nearestPointOf(p: Vector3f, qs: Iterable[Vector3f]) =
    var minD = Float.MaxValue
    var minQ = qs.head
    for q <- qs do
      val d = p.distanceSquared(q)
      if d < minD then
        minD = d
        minQ = q
    minQ

  private def alignTopPositions(qs: ArrayBuffer[Vector3f]) = 
    val pNE = Vector3f(2 * rad1, +rad2, +radZ)
    val pSE = Vector3f(2 * rad1, -rad2, +radZ)
    val qNE = nearestPointOf(pNE, qs)
    val qSE = nearestPointOf(pSE, qs)
    qNE.set(pNE)
    qSE.set(pSE)

  private def alignBottomPositions(qs: ArrayBuffer[Vector3f]) = 
    val pNE = Vector3f(2 * rad1, +rad2, -radZ)
    val pSE = Vector3f(2 * rad1, -rad2, -radZ)
    val qNE = nearestPointOf(pNE, qs)
    val qSE = nearestPointOf(pSE, qs)
    qNE.set(pNE)
    qSE.set(pSE)

  private def geometry(m: Mesh) =
    val res = new Geometry("", m)
    res.setMaterial(mat)
    res

  override def clone() = this

  
  // FastMath ext

  // 0 <= angle < 360

  def angle(x: Float, y: Float) =
    val abs = sqrt(x * x + y * y)
    val aco = acos(x / abs) //       0 <= acos <= pi
    val asi = asin(y / abs) // -pi / 2 <= asin <= pi / 2
    val ang = if y >= 0 then aco else 2 * PI - aco
    ang // * 180 / Pi

