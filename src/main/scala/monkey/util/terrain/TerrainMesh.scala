package monkey.util.terrain

import com.jme3.asset.AssetManager
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Mesh
import com.jme3.scene.Node
import com.jme3.scene.VertexBuffer
import com.jme3.terrain.heightmap.AbstractHeightMap
import com.jme3.util.BufferUtils
import monkey.util.extension.MonkeyExt

import scala.collection.mutable.ArrayBuffer

class TerrainMesh(val map: AbstractHeightMap) extends Mesh, MonkeyExt:

  // val colorProvider = SimpleColorProvider()

  init()

  def init() =
    val num = map.getSize()
    val ext = map.findMinMaxHeights()
    val min = ext(0)
    val max = ext(1)
    println(s"$min - $max")
    val off = -0.5f * (num - 1)
    val pa = ArrayBuffer[Vector3f]()
    val na = ArrayBuffer[Vector3f]()
    // val ca = ArrayBuffer[ColorRGBA]()
    for j <- 0 to num - 1 do
      for i <- 0 to num - 1 do
        val z = map.getTrueHeightAtPoint(i, j)
        pa += Vector3f(off + i, off + j, z)
        na += map.normalAt(i, j)
        // ca += colorProvider.colorFor(z / max) // (z - min) / max
    val ia = ArrayBuffer[Int]()
    for j <- 0 to num - 2 do
      for i <- 0 to num - 2 do
        if (i + j) % 2 == 0 then
          ia += i + 0 + (j + 0) * num
          ia += i + 1 + (j + 0) * num
          ia += i + 1 + (j + 1) * num
          ia += i + 1 + (j + 1) * num
          ia += i + 0 + (j + 1) * num
          ia += i + 0 + (j + 0) * num
        else
          ia += i + 1 + (j + 0) * num
          ia += i + 1 + (j + 1) * num
          ia += i + 0 + (j + 1) * num
          ia += i + 0 + (j + 1) * num
          ia += i + 0 + (j + 0) * num
          ia += i + 1 + (j + 0) * num

    setMode(Mesh.Mode.Triangles)
    setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(pa.toArray*))
    setBuffer(VertexBuffer.Type.Normal, 3, BufferUtils.createFloatBuffer(na.toArray*))
    // setBuffer(VertexBuffer.Type.Color, 4, BufferUtils.createFloatBuffer(ca.toArray*))
    setBuffer(VertexBuffer.Type.Index, 1, BufferUtils.createIntBuffer(ia.toArray*))
/*
  def normalAt(i: Int, j: Int): Vector3f =
    val num = map.getSize()
    val u = Vector3f()
    val v = Vector3f()
    val n = Vector3f()
    if i < num - 1 then
      val z0 = map.getTrueHeightAtPoint(i + 0, j)
      val z1 = map.getTrueHeightAtPoint(i + 1, j)
      u.set(1f, 0f, z1 - z0)
    else
      val z0 = map.getTrueHeightAtPoint(i - 1, j)
      val z1 = map.getTrueHeightAtPoint(i - 0, j)
      u.set(1f, 0f, z1 - z0)
    if (j < num - 1)
      val z0 = map.getTrueHeightAtPoint(i, j + 0)
      val z1 = map.getTrueHeightAtPoint(i, j + 1)
      v.set(0f, 1f, z1 - z0)
    else
      val z0 = map.getTrueHeightAtPoint(i, j - 1)
      val z1 = map.getTrueHeightAtPoint(i, j - 0)
      v.set(0f, 1f, z1 - z0)

    u.cross(v, n)
    n.normalizeLocal()
    // println(n)
    // n
*/