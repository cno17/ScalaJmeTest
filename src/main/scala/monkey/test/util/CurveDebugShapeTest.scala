package monkey.test.util

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.input.event.MouseMotionEvent
import com.jme3.math.FastMath
import com.jme3.math.Vector3f
import monkey.util.application.simple.SimpleApp
import monkey.util.math.Rnd
import monkey.util.shape.CurveDebugShape

import scala.collection.mutable.ArrayBuffer

object CurveDebugShapeTest extends SimpleApp, Rnd:

  val rad = 10f
  val num = 30
  var shape: CurveDebugShape = null

  override def init() =
    camera3D.setTranslation(0, 0, 50)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    shape = CurveDebugShape(ps(rad, num), assetManager)
    rootNode3D.addChild(shape)
    keyListeners += onKeyEvent
    mouseMotionListeners += onMouseMotionEvent

  def ps(r: Float, n: Int) =
    val buf = ArrayBuffer[Vector3f]()
    var rad = r
    for i <- 0 to n - 1 do
      rad += rndF(-rad * 0.1f, rad * 0.1f)
      println(rad)
      val a = i * FastMath.TWO_PI / n
      val x = rad * FastMath.cos(a) // + rndF(-noise, noise)
      val y = rad * FastMath.sin(a) // + rndF(-noise, noise)
      buf += V(x, y, -rad)
    buf.toArray

  def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed && e.getKeyCode == KeyInput.KEY_N then
      rootNode3D.removeChild(shape)
      shape = CurveDebugShape(ps(rad, num), assetManager)
      rootNode3D.addChild(shape)
      println(17)

  def onMouseMotionEvent(e: MouseMotionEvent) =
    val dx = e.getDX * 0.01f
    val dy = e.getDY * 0.01f
    rootNode3D.rotate(dy, 0, 0)
    rootNode3D.rotate(0, 0, dx)
