package monkey.util.application

import com.jme3.app.Application
import com.jme3.input.InputManager
import com.jme3.input.JoyInput
import com.jme3.input.KeyInput
import com.jme3.input.MouseInput
import com.jme3.input.RawInputListener
import com.jme3.input.TouchInput
import com.jme3.input.event.JoyAxisEvent
import com.jme3.input.event.JoyButtonEvent
import com.jme3.input.event.KeyInputEvent
import com.jme3.input.event.MouseButtonEvent
import com.jme3.input.event.MouseMotionEvent
import com.jme3.input.event.TouchEvent
import com.jme3.system.JmeContext
import com.jme3.system.lwjgl.LwjglWindow
import monkey.util.lwjgl.glfw.GlfwKey
import monkey.util.lwjgl.glfw.GlfwKey.LeftAlt
import monkey.util.lwjgl.glfw.GlfwKey.LeftControl
import monkey.util.lwjgl.glfw.GlfwKey.LeftShift
import monkey.util.lwjgl.glfw.GlfwKey.RightAlt
import monkey.util.lwjgl.glfw.GlfwKey.RightControl
import monkey.util.lwjgl.glfw.GlfwKey.RightShift
import monkey.util.lwjgl.glfw.GlfwMouseButton
import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.glfwGetKey
import org.lwjgl.glfw.GLFW.glfwGetMouseButton

import scala.collection.mutable.ListBuffer

trait InputComponent extends Application:

  var windowHandle: Long = 0
  var mouseInput: MouseInput = null
  var keyInput: KeyInput = null
  var joyInput: JoyInput = null
  var touchInput: TouchInput = null
  var inputManager: InputManager = null

  val mouseButtonListeners = ListBuffer[MouseButtonEvent => Unit]()
  val mouseMotionListeners = ListBuffer[MouseMotionEvent => Unit]()
  val keyListeners = ListBuffer[KeyInputEvent => Unit]()
  val joyButtonListeners = ListBuffer[JoyButtonEvent => Unit]()
  val joyAxisListeners = ListBuffer[JoyAxisEvent => Unit]()
  val touchListeners = ListBuffer[TouchEvent => Unit]()

  def context: JmeContext

  def initInput() =
    windowHandle = context.asInstanceOf[LwjglWindow].getWindowHandle
    mouseInput = context.getMouseInput
    if mouseInput != null then mouseInput.initialize()
    keyInput = context.getKeyInput
    if keyInput != null then keyInput.initialize()
    joyInput = context.getJoyInput
    if joyInput != null then joyInput.initialize()
    touchInput = context.getTouchInput
    if touchInput != null then touchInput.initialize()
    inputManager = InputManager(mouseInput, keyInput, joyInput, touchInput)
    inputManager.addRawInputListener(RawInputListenerImpl())

  def updateInput(tpf: Float) =
    inputManager.update(tpf)

  def exitInput() =
    if mouseInput != null then mouseInput.destroy()
    if keyInput != null then keyInput.destroy()
    if joyInput != null then joyInput.destroy()
    if touchInput != null then touchInput.destroy()

  def isKeyDown(key: GlfwKey) = glfwGetKey(windowHandle, key.id) == GLFW_PRESS
  def isAltKeyDown = isKeyDown(LeftAlt) || isKeyDown(RightAlt)
  def isControlKeyDown = isKeyDown(LeftControl) || isKeyDown(RightControl)
  def isShiftKeyDown = isKeyDown(LeftShift) || isKeyDown(RightShift)
  def isMouseButtonDown(button: GlfwMouseButton) = glfwGetMouseButton(windowHandle, button.id) == GLFW_PRESS

  override def getInputManager = inputManager

  class RawInputListenerImpl extends RawInputListener:
    override def beginInput() = {}
    override def onKeyEvent(e: KeyInputEvent) = for l <- keyListeners do l(e)
    override def onMouseButtonEvent(e: MouseButtonEvent) = for l <- mouseButtonListeners do l(e)
    override def onMouseMotionEvent(e: MouseMotionEvent) = for l <- mouseMotionListeners do l(e)
    override def onJoyButtonEvent(e: JoyButtonEvent) = for l <- joyButtonListeners do l(e)
    override def onJoyAxisEvent(e: JoyAxisEvent) = for l <- joyAxisListeners do l(e)
    override def onTouchEvent(e: TouchEvent) = for l <- touchListeners do l(e)
    override def endInput() = {}
