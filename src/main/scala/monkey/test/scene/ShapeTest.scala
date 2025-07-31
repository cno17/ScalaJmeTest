package monkey.test.scene

import com.jme3.scene.VertexBuffer
import com.jme3.scene.shape.Torus
import monkey.util.application.simple.SimpleApp

object ShapeTest extends SimpleApp:

  override def init() =
    val mesh = Torus(10, 10, 1f, 3f)
    println(mesh.getBuffer(VertexBuffer.Type.Position))
    println(mesh.getBuffer(VertexBuffer.Type.Normal))
    println(mesh.getBuffer(VertexBuffer.Type.Tangent))
    println(mesh.getBuffer(VertexBuffer.Type.TexCoord))