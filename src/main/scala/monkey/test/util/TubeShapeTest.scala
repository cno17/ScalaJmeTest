package monkey.test.util

import com.jme3.input.event.MouseMotionEvent
import monkey.util.application.simple.SimpleApp
import monkey.util.material.ColorMaterial
import monkey.util.math.Arrow
import monkey.util.math.curve.HermiteSegment
import monkey.util.shape.HermiteSegmentShape
import monkey.util.shape.TubeShape

object TubeShapeTest extends SimpleApp:

  var curve: HermiteSegment = null
  var shape: TubeShape = null

  override def init() =
    camera3D.setTranslation(0, 0, 20)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.8f, 0.8f, 0.8f))
    mat.setWireframe(true)
    curve = hs()
    shape = TubeShape(curve, 0.3f, 50, 8, mat)
    rootNode3D.addChild(shape)
    rootNode3D.addChild(HermiteSegmentShape(curve, 50, mat))
    mouseMotionListeners += onMouseMotionEvent

  def hs() =
    val a0 = Arrow(V(-5, +2, 3), V(10, +5, +2))
    val a1 = Arrow(V(+5, -4, 2), V(10, -3, -5))
    HermiteSegment(a0, a1)

  def onMouseMotionEvent(e: MouseMotionEvent) =
    val dx = e.getDX * 0.01f
    val dy = e.getDY * 0.01f
    rootNode3D.rotate(dy, 0, 0)
    rootNode3D.rotate(0, 0, dx)
