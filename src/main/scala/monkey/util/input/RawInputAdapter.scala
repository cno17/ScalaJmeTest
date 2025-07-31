package monkey.util.input

import com.jme3.input.RawInputListener
import com.jme3.input.event.*

trait RawInputAdapter extends RawInputListener:

  override def beginInput() = {}
  override def onKeyEvent(e: KeyInputEvent) = {}
  override def onMouseButtonEvent(e: MouseButtonEvent) = {}
  override def onMouseMotionEvent(e: MouseMotionEvent) = {}
  override def onJoyButtonEvent(e: JoyButtonEvent) = {}
  override def onJoyAxisEvent(e: JoyAxisEvent) = {}
  override def onTouchEvent(e: TouchEvent) = {}
  override def endInput() = {}
