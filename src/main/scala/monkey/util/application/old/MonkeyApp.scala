package monkey.util.application.old

import com.jme3.app.DebugKeysAppState
import com.jme3.app.FlyCamAppState
import com.jme3.app.SimpleApplication
import com.jme3.app.StatsAppState
import com.jme3.app.state.AppState
import com.jme3.input.RawInputListener
import com.jme3.input.event.JoyAxisEvent
import com.jme3.input.event.JoyButtonEvent
import com.jme3.input.event.KeyInputEvent
import com.jme3.input.event.MouseButtonEvent
import com.jme3.input.event.MouseMotionEvent
import com.jme3.input.event.TouchEvent
import com.jme3.system.AppSettings
import monkey.util.extension.MonkeyExt

import scala.collection.mutable.ListBuffer

// TODO: Lights

// class MonkeyApp(val w: Int, val h: Int, states: Array[AppState])
//   extends SimpleApplication(states: _*),
//     RawInputAdapter,
//     MonkeyExt:

class MonkeyApp(val width: Int, val height: Int) extends SimpleApplication, MonkeyExt:

  def this() = this(800, 600)
  
  val keyListeners = ListBuffer[KeyInputEvent => Unit]()
  val mouseButtonListeners = ListBuffer[MouseButtonEvent => Unit]()
  val mouseMotionListeners = ListBuffer[MouseMotionEvent => Unit]()
  val joyButtonListeners = ListBuffer[JoyButtonEvent => Unit]()
  val joyAxisListeners = ListBuffer[JoyAxisEvent => Unit]()
  val touchListeners = ListBuffer[TouchEvent => Unit]()
  
  def removeInitialAppStates() =
    val sm = stateManager
    sm.detach(sm.getState(classOf[DebugKeysAppState]))
    sm.detach(sm.getState(classOf[FlyCamAppState]))
    sm.detach(sm.getState(classOf[StatsAppState]))

  def main(args: Array[String]) =
    val s = AppSettings(true)
    s.setWidth(width)
    s.setHeight(height)
    setSettings(s)
    setShowSettings(false)
    start()

  override def simpleInitApp() =
    inputManager.addRawInputListener(RawInputListenerImpl())
  
  private class RawInputListenerImpl extends RawInputListener:
    override def beginInput() = {}
    override def onKeyEvent(e: KeyInputEvent) = for l <- keyListeners do l(e)
    override def onMouseButtonEvent(e: MouseButtonEvent) = for l <- mouseButtonListeners do l(e)
    override def onMouseMotionEvent(e: MouseMotionEvent) = for l <- mouseMotionListeners do l(e)
    override def onJoyButtonEvent(e: JoyButtonEvent) = for l <- joyButtonListeners do l(e)
    override def onJoyAxisEvent(e: JoyAxisEvent) = for l <- joyAxisListeners do l(e)
    override def onTouchEvent(e: TouchEvent) = for l <- touchListeners do l(e)
    override def endInput() = {}



