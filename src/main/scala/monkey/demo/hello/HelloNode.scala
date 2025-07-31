package monkey.demo.hello

import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp
import monkey.util.material.ColorMaterial

object HelloNode extends SimpleApp:

  var pivot: Node = null

  override def init() =

    val mat1 = ColorMaterial(assetManager)
    val mat2 = ColorMaterial(assetManager)
    mat1.setColor(C(0.8f, 0.2f, 0.2f))
    mat2.setColor(C(0.2f, 0.8f, 0.2f))
    val box1 = Geometry(Box(1.0f, 0.6f, 0.2f), mat1)
    val box2 = Geometry(Box(1.0f, 1.0f, 1.0f), mat2)
    box1.translate(1f, -1f, 1f)
    box2.translate(1f, 3f, 1f)
    pivot = Node("pivot")
    pivot.attachChild(box1)
    pivot.attachChild(box2)
    pivot.rotate(0.4f, 0.4f, 0.0f)
    rootNode3D.attachChild(pivot)

  override def update(tpf: Float) =
    pivot.rotate(0, 0.01f, 0)
