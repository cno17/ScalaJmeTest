package monkey.test.lwjgl

import com.jme3.renderer.Camera
import com.jme3.renderer.RenderManager
import com.jme3.renderer.Renderer
import com.jme3.renderer.ViewPort
import com.jme3.system.AppSettings
import com.jme3.system.JmeContext
import com.jme3.system.JmeSystem
import com.jme3.system.NanoTimer
import com.jme3.system.SystemListener
import com.jme3.system.Timer

/**
 * exit with RMB
 * why is the context not renderable
 * why is the key input not initialized and throws an exeption on destroy?
 */

object RenderableTest extends SystemListener:

  var timer: Timer = null
  var settings: AppSettings = null
  var context: JmeContext = null
  var renderer: Renderer = null
  var renderManager: RenderManager = null
  var camera: Camera = null
  var viewPort: ViewPort = null


  // SystemListener
  override def initialize() =
    println("initialize")
    initVideo()
    println(context.isCreated)
    println(context.isRenderable)

  override def update() = println("update")
  override def destroy() = println("destroy")
  override def gainFocus() = {}
  override def loseFocus() = {}
  override def reshape(w: Int, h: Int) = {}
  override def rescale(x: Float, y: Float) = {}
  override def requestClose(esc: Boolean) = {}
  override def handleError(msg: String, t: Throwable) = println(msg)

  def main(args: Array[String]) =
    timer = NanoTimer()
    settings = AppSettings(true)
    settings.setRenderer(AppSettings.LWJGL_OPENGL45)
    context = JmeSystem.newContext(settings, JmeContext.Type.Display)
    context.setSystemListener(this)
    context.create(true)
    context.destroy(true)

  private def initVideo() =
    renderer = context.getRenderer
    renderManager = RenderManager(renderer)
    renderManager.setTimer(timer)
    camera = Camera(settings.getWidth, settings.getHeight)
    viewPort = renderManager.createMainView("Default", camera)
    viewPort.setClearFlags(true, true, true)
