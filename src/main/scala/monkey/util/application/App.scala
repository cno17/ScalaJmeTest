package monkey.util.application

import com.jme3.app.Application
import com.jme3.app.LostFocusBehavior
import com.jme3.app.state.AppStateManager
import com.jme3.asset.AssetManager
import com.jme3.asset.plugins.ClasspathLocator
import com.jme3.asset.plugins.FileLocator
import com.jme3.audio.AudioRenderer
import com.jme3.audio.Listener
import com.jme3.input.event.KeyInputEvent
import com.jme3.profile.AppProfiler
import com.jme3.renderer.RenderManager
import com.jme3.system.AppSettings
import com.jme3.system.JmeContext
import com.jme3.system.JmeSystem
import com.jme3.system.NanoTimer
import com.jme3.system.SystemListener
import com.jme3.system.Timer
import monkey.util.extension.MonkeyExt

import java.nio.file.Path
import java.util.concurrent.Callable
import scala.collection.mutable.ListBuffer

trait App extends InputComponent, Application, SystemListener, MonkeyExt:

  var assetDir = Path.of("").toAbsolutePath.getParent.resolve("JmeData").toFile

  var settings: AppSettings = null
  var timer: Timer = null
  var context: JmeContext = null
  var assetManager: AssetManager = null
  var stateManager: AppStateManager = null
  
  // override in user subclasses
  
  def createSettings() =
    val res = AppSettings(true)
    res.setWidth(1200)
    res.setHeight(900)
    res.setResizable(true)
    res.setRenderer(AppSettings.LWJGL_OPENGL45)
    res    

  def init() = {}
  def update(tpf: Float) = {}
  def render(rm: RenderManager) = {}
  def exit() = {}

  // override in system subclasses
  
  def initApp(): Unit
  def updateApp(tpf: Float): Unit
  def exitApp(): Unit

  def main(args: Array[String]) =
    start()

  // Application

  override def getAppProfiler = null
  override def getAssetManager = assetManager
  override def getContext = context
  override def getLostFocusBehavior = null
  override def getStateManager = stateManager
  override def getTimer = timer

  override def isPauseOnLostFocus = false

  override def setAppProfiler(p: AppProfiler) = {}
  override def setLostFocusBehavior(b: LostFocusBehavior) = {}
  override def setPauseOnLostFocus(b: Boolean) = {}
  override def setSettings(s: AppSettings) = settings = s
  override def setTimer(t: Timer) = timer = t

  override def getAudioRenderer = null
  override def getListener = null

  override def start(): Unit = start(true)

  override def start(wait: Boolean) =
    settings = createSettings()
    JmeSystem.initialize(settings)
    timer = NanoTimer()
    context = JmeSystem.newContext(settings, JmeContext.Type.Display)
    context.setSystemListener(this)
    context.create(wait)
    assetManager = JmeSystem.newAssetManager()
    assetManager.registerLocator(assetDir.getAbsolutePath, classOf[FileLocator])
    assetManager.registerLocator("", classOf[ClasspathLocator])
    assetManager.initLoaders()
    stateManager = AppStateManager(this)

  override def stop(): Unit = stop(true)

  override def stop(wait: Boolean) =
    context.destroy(wait)

  override def restart() = {}

  override def enqueue(r: Runnable) = throw Exception()
  override def enqueue[V](c: Callable[V]) = throw Exception()

  // SystemListener

  override def initialize() =
    initApp()

  override def update() =
    val tpf = timer.getTimePerFrame
    timer.update()
    updateApp(tpf)

  override def destroy() =
    exitApp()

  override def gainFocus() = {}

  override def loseFocus() = {}

  override def requestClose(esc: Boolean) =
    // exitApp()
    context.destroy(false)
    println("requestClose")

  override def handleError(msg: String, t: Throwable) =
    JmeSystem.handleErrorMessage(msg + "\n" + t.getClass.getSimpleName + ": " + t.getMessage)
    stop()
