package monkey.test.util

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import monkey.util.application.simple.SimpleApp
import monkey.util.material.ColorMaterial
import monkey.util.math.Arrow
import monkey.util.math.curve.HermiteSegment
import monkey.util.shape.HermiteSegmentShape

object HermiteSegmentShapeTest extends SimpleApp:

  var shape: HermiteSegmentShape = null

  override def init() =
    camera3D.setTranslation(0, 0, 20)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.8f, 0.8f, 0.8f))
    mat.setWireframe(true)
    shape = HermiteSegmentShape(hs(), 20, mat)
    rootNode3D.addChild(shape)
    keyListeners += onKeyEvent

  def hs() =
    val a0 = Arrow(V(-5, 0, 0), V(10, +10, 0))
    val a1 = Arrow(V(+5, 0, 0), V(10, +10, 0))
    HermiteSegment(a0, a1)

  def onKeyEvent(e: KeyInputEvent) =
    if e.getKeyCode == KeyInput.KEY_UP then println(2)
