package monkey.test.system

import com.jme3.math.Vector3f
import com.jme3.math.Vector3f
import com.jme3.math.Vector3f
import com.jme3.renderer.Camera
import com.jme3.renderer.RenderManager
import com.jme3.scene.Node
import com.jme3.system.AppSettings
import com.jme3.system.JmeContext
import com.jme3.system.JmeSystem
import com.jme3.system.SystemListener

// how to create a renderable context?

object ContextTest extends SystemListener:

  var settings: AppSettings = null
  var context: JmeContext = null

  override def initialize() =
    val renderer = context.getRenderer
    val renderManager = RenderManager(renderer)
    // renderManager.setTimer(timer)
    val camera = Camera(settings.getWidth, settings.getHeight)
    val viewPort = renderManager.createMainView("Default", camera)
    viewPort.setClearFlags(true, true, true)


  println("initialize")
  override def update() = println("update")
  override def destroy() = println("destroy")
  override def reshape(width: Int, height: Int) = println("reshape")
  override def gainFocus() = println("gainFocus")
  override def loseFocus() = println("loseFocus")
  override def requestClose(esc: Boolean) = println("requestClose")
  override def handleError(errorMsg: String, t: Throwable) = println("handleError")

  def main(args: Array[String]) =
    settings = AppSettings(true)
    JmeSystem.initialize(settings)
    context = JmeSystem.newContext(settings, JmeContext.Type.Display)
    context.setSystemListener(this)
    context.create(true)
    println(context.isCreated)
    println(context.isRenderable)
    context.destroy(true)
    println("ok")
