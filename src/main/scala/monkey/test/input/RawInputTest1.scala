package monkey.test.input

import com.jme3.app.SimpleApplication
import com.jme3.input.event.KeyInputEvent
import com.jme3.input.event.MouseButtonEvent
import com.jme3.input.event.MouseMotionEvent

object RawInputTest1 extends SimpleApplication, RawInputAdapter:

  override def simpleInitApp() =
    inputManager.addRawInputListener(this)

  override def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed then println(e.getKeyChar)

  override def onMouseButtonEvent(e: MouseButtonEvent) =
    if e.isPressed then println("($e.x, $e.y)")

  override def onMouseMotionEvent(e: MouseMotionEvent) =
    println(e.getDX)

  def main(args: Array[String]) =
    start()
  

