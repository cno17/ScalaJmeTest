package monkey.util.application.legacy

import com.jme3.asset.AssetManager
import com.jme3.input.*
import com.jme3.renderer.Camera
import com.jme3.renderer.RenderManager
import com.jme3.renderer.Renderer
import com.jme3.renderer.ViewPort
import com.jme3.system.*
import com.jme3.system.JmeContext.Type

class LegacyApp extends SystemListener:

  var timer: Timer = null
  var settings: AppSettings = null
  var context: JmeContext = null
  var renderer: Renderer = null
  var renderManager: RenderManager = null
  var camera: Camera = null
  var viewPort: ViewPort = null
  var mouseInput: MouseInput = null
  var keyInput: KeyInput = null
  var inputManager: InputManager = null

  def start() =
    timer = NanoTimer()
    settings = AppSettings(true)
    context = JmeSystem.newContext(settings, JmeContext.Type.Display)
    context.setSystemListener(this)
    context.create(true)

  def stop() =
    context.destroy(true)

  override def reshape(w: Int, h: Int): Unit =
    if (renderManager != null) renderManager.notifyReshape(w, h)

  override def rescale(x: Float, y: Float): Unit =
    if (renderManager != null) renderManager.notifyRescale(x, y)

  override def initialize(): Unit =
    println("initialize")
    initVideo()
    initInput()
    // update timer so that the next delta is not too large
    timer.update();
    timer.reset()
    // user code here

  override def handleError(msg: String, t: Throwable): Unit =
    JmeSystem.handleErrorMessage(msg + "\n" + t.getClass.getSimpleName + ": " + t.getMessage)
    stop()

  override def gainFocus() = {}

  override def loseFocus() = {}

  override def requestClose(esc: Boolean): Unit =
    context.destroy(false)

  override def update(): Unit =
    timer.update()
    inputManager.update(timer.getTimePerFrame)
    // user code here

  override def destroy(): Unit =
    keyInput.destroy()
    mouseInput.destroy()
    inputManager = null
  
  private def initVideo(): Unit =
    renderer = context.getRenderer
    renderManager = RenderManager(renderer)
    renderManager.setTimer(timer)
    camera = Camera(settings.getWidth, settings.getHeight)
    viewPort = renderManager.createMainView("Default", camera)
    viewPort.setClearFlags(true, true, true)
  
  private def initInput(): Unit =
    mouseInput = context.getMouseInput
    mouseInput.initialize()
    keyInput = context.getKeyInput
    keyInput.initialize()
    inputManager = InputManager(mouseInput, keyInput, null, null)
