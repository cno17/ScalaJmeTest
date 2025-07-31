package monkey.test.application

import com.jme3.input.event.MouseMotionEvent
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp
import monkey.util.material.ColorMaterial

object SimpleTest extends SimpleApp:

  var pivot: Node = null

  override def init() =
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.8f, 0.2f, 0.2f))
    val geo = Geometry(Box(1.0f, 0.6f, 0.2f), mat)
    geo.translate(1, -1, 1)
    pivot = Node("pivot")
    pivot.attachChild(geo)
    pivot.rotate(0.4f, 0.4f, 0.0f)
    rootNode3D.attachChild(pivot)
    mouseMotionListeners += handleMouseMotion

  override def update(tpf: Float) =
    pivot.rotate(0, 0.01f, 0)

  def handleMouseMotion(e: MouseMotionEvent) =
    println(e.getX)