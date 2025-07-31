package monkey.util.shape

import com.jme3.math.Vector2f
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import com.jme3.scene.Node
import monkey.util.material.SimpleMaterial

import scala.collection.mutable.ArrayBuffer

class GridShape(val cellExtentX: Float, val cellExtentY: Float, cellCountX: Int, cellCountY: Int, mat: SimpleMaterial)
    extends Shape:

  init()

  def extentX = cellExtentX * cellCountX
  def extentY = cellExtentY * cellCountY

  def radiusX = extentX * 0.5f
  def radiusY = extentY * 0.5f

  def centerX = getWorldTranslation().x + radiusX
  def centerY = getWorldTranslation().y + radiusY

  private def init() =
    positionA = ArrayBuffer[Vector3f]()
    normalA = ArrayBuffer[Vector3f]()
    texCoordA = ArrayBuffer[Vector2f]()
    indexA = ArrayBuffer[Int]()
    val cx = cellCountX
    val cy = cellCountY
    for j <- 0 to cy do
      val y = j * cellExtentY
      for i <- 0 to cx do
        val x = i * cellExtentX
        positionA += V(x, y, 0)
        normalA += V(0, 0, 1)
        texCoordA += V(i % 2, j % 2)
    for j <- 0 to cy - 1 do
      for i <- 0 to cx - 1 do
        val i00 = (j + 0) * (cx + 1) + (i + 0)
        val i10 = (j + 0) * (cx + 1) + (i + 1)
        val i01 = (j + 1) * (cx + 1) + (i + 0)
        val i11 = (j + 1) * (cx + 1) + (i + 1)
        if (i + j) % 2 == 0 then
          indexA ++= Array(i00, i10, i11)
          indexA ++= Array(i11, i01, i00)
        else
          indexA ++= Array(i10, i11, i01)
          indexA ++= Array(i01, i00, i10)
    addChild(Geometry(createMesh(Mesh.Mode.Triangles), mat))
