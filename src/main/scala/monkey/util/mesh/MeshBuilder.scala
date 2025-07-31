package monkey.util.mesh

import com.jme3.math.Vector3f
import com.jme3.math.Vector4f
import com.jme3.scene.Mesh
import com.jme3.scene.VertexBuffer
import com.jme3.util.BufferUtils

trait MeshBuilder:
  
  def createTriangleMesh(pa: Array[Vector3f], na: Array[Vector3f], ia: Array[Int]) =
    val pb = BufferUtils.createFloatBuffer(pa: _*)
    val nb = BufferUtils.createFloatBuffer(na: _*)
    val ib = BufferUtils.createIntBuffer(ia: _*)
    val mesh = Mesh()
    mesh.setMode(Mesh.Mode.Triangles)
    mesh.setBuffer(VertexBuffer.Type.Position, 3, pb)
    mesh.setBuffer(VertexBuffer.Type.Normal, 3, nb)
    mesh.setBuffer(VertexBuffer.Type.Index, 1, ib)
    mesh

  def createTriangleMesh(
    pa: Array[Vector3f],
    na: Array[Vector3f],
    ca: Array[Vector4f],
    ia: Array[Int]) =
    val pb = BufferUtils.createFloatBuffer(pa: _*)
    val nb = BufferUtils.createFloatBuffer(na: _*)
    val cb = BufferUtils.createFloatBuffer(ca: _*)
    val ib = BufferUtils.createIntBuffer(ia: _*)
    val mesh = Mesh()
    mesh.setMode(Mesh.Mode.Triangles)
    mesh.setBuffer(VertexBuffer.Type.Position, 3, pb)
    mesh.setBuffer(VertexBuffer.Type.Normal, 3, nb)
    mesh.setBuffer(VertexBuffer.Type.Color, 4, cb)
    mesh.setBuffer(VertexBuffer.Type.Index, 1, ib)
    mesh

