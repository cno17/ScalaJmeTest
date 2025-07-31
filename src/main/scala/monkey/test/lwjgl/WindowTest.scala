package monkey.test.lwjgl

import com.jme3.input.InputManager
import com.jme3.input.KeyInput
import com.jme3.input.MouseInput
import com.jme3.input.RawInputListener
import com.jme3.input.event.JoyAxisEvent
import com.jme3.input.event.JoyButtonEvent
import com.jme3.input.event.KeyInputEvent
import com.jme3.input.event.MouseButtonEvent
import com.jme3.input.event.MouseMotionEvent
import com.jme3.input.event.TouchEvent
import com.jme3.system.AppSettings
import com.jme3.system.JmeContext
import com.jme3.system.SystemListener
import com.jme3.system.lwjgl.LwjglDisplay

/**
 * exit with RMB
 * why is the context not renderable
 * why is the key input not initialized and throws an exeption on destroy?
 */

object WindowTest extends SystemListener, RawInputListener:

  var context: JmeContext = null
  var mouseInput: MouseInput = null
  var keyInput: KeyInput = null

  // SystemListener
  override def initialize() = println("initialize")
  override def update() =
    mouseInput.update()
    keyInput.update()
  override def destroy() = println("destroy")
  override def gainFocus() = println("gainFocus")
  override def loseFocus() = println("loseFocus")
  override def reshape(w: Int, h: Int) = {}
  override def requestClose(esc: Boolean) = {}
  override def handleError(msg: String, t: Throwable) = println(msg)

  // RawInputListener
  override def beginInput() =  {}
  override def onJoyAxisEvent(evt: JoyAxisEvent) = {}
  override def onJoyButtonEvent(evt: JoyButtonEvent) = {}
  override def onMouseMotionEvent(evt: MouseMotionEvent) = {}
  override def onMouseButtonEvent(evt: MouseButtonEvent) = if evt.getButtonIndex == 1 then exit()
  override def onKeyEvent(evt: KeyInputEvent) = println(evt)
  override def onTouchEvent(evt: TouchEvent) = {}
  override def endInput() = {}

  def main(args: Array[String]) =
    init()

  def init() =
    val settings = AppSettings(true)
    settings.setResizable(true)
    settings.setWidth(640)
    settings.setHeight(320)
    settings.setCenterWindow(false)
    settings.setWindowXPosition(100)
    settings.setWindowYPosition(100)
    settings.setRenderer(AppSettings.LWJGL_OPENGL45)
    settings.setUseInput(true)
    settings.setEmulateKeyboard(false)
    context = LwjglDisplay()
    context.setSettings(settings)
    context.setSystemListener(this)
    context.create(true)
    mouseInput = context.getMouseInput
    mouseInput.setInputListener(this)
    mouseInput.initialize()
    keyInput = context.getKeyInput
    keyInput.setInputListener(this)
    keyInput.initialize()
    val joyInput = context.getJoyInput
    joyInput.initialize()
    println(joyInput)
    println(joyInput.isInitialized)
    println(context.isCreated)
    println(context.isRenderable)
    println(mouseInput.isInitialized)
    println(keyInput.isInitialized)
    keyInput.destroy()
    mouseInput.destroy()
    context.destroy(true)

  def exit() =
    context.destroy(true)

