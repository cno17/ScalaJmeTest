package monkey.util.shape

import com.jme3.math.ColorRGBA
import com.jme3.math.Vector2f
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import com.jme3.scene.Node
import com.jme3.scene.VertexBuffer
import com.jme3.util.BufferUtils
import monkey.util.extension.MonkeyExt

import java.nio.FloatBuffer
import java.nio.IntBuffer
import scala.collection.mutable.ArrayBuffer

trait Shape extends Node, MonkeyExt:

  protected var positionA: ArrayBuffer[Vector3f] = null
  protected var normalA: ArrayBuffer[Vector3f] = null
  protected var tangentA: ArrayBuffer[Vector3f] = null
  protected var colorA: ArrayBuffer[ColorRGBA] = null
  protected var texCoordA: ArrayBuffer[Vector2f] = null
  protected var indexA: ArrayBuffer[Int] = null

  protected def createMesh(mode: Mesh.Mode) =
    var positionB: FloatBuffer = null
    var normalB: FloatBuffer = null
    var tangentB: FloatBuffer = null
    var colorB: FloatBuffer = null
    var texCoordB: FloatBuffer = null
    var indexB: IntBuffer = null
    if positionA != null then positionB = BufferUtils.createFloatBuffer(positionA.toArray*)
    if normalA != null then normalB = BufferUtils.createFloatBuffer(normalA.toArray*)
    if tangentA != null then tangentB = BufferUtils.createFloatBuffer(tangentA.toArray*)
    if colorA != null then colorB = BufferUtils.createFloatBuffer(colorA.toArray*)
    if texCoordA != null then texCoordB = BufferUtils.createFloatBuffer(texCoordA.toArray*)
    if indexA != null then indexB = BufferUtils.createIntBuffer(indexA.toArray*)
    val mesh = Mesh()
    mesh.setMode(mode)
    if positionB != null then mesh.setBuffer(VertexBuffer.Type.Position, 3, positionB)
    if normalB != null then mesh.setBuffer(VertexBuffer.Type.Normal, 3, normalB)
    if tangentB != null then mesh.setBuffer(VertexBuffer.Type.Tangent, 3, tangentB)
    if colorB != null then mesh.setBuffer(VertexBuffer.Type.Color, 4, colorB)
    if texCoordB != null then mesh.setBuffer(VertexBuffer.Type.TexCoord2, 2, texCoordB)
    if indexB != null then mesh.setBuffer(VertexBuffer.Type.Index, 1, indexB)
    mesh

  override def clone() = this
