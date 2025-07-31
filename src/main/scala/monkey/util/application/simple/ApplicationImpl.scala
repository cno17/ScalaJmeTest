package monkey.util.application.simple

import com.jme3.app.Application
import com.jme3.app.LostFocusBehavior
import com.jme3.app.state.AppStateManager
import com.jme3.asset.plugins.ClasspathLocator
import com.jme3.asset.plugins.FileLocator
import com.jme3.profile.AppProfiler
import com.jme3.system.AppSettings
import com.jme3.system.JmeContext
import com.jme3.system.JmeSystem
import com.jme3.system.Timer
import com.jme3.system.NanoTimer

import java.util.concurrent.Callable

trait ApplicationImpl extends Application:

  this: SimpleApp =>

  override def getAppProfiler = null
  override def getAssetManager = assetManager
  override def getAudioRenderer = null
  override def getCamera = camera3D
  override def getContext = context
  override def getGuiViewPort = viewPort2D
  override def getInputManager = inputManager
  override def getListener = null
  override def getLostFocusBehavior = null
  override def getRenderer = renderer
  override def getRenderManager = renderManager
  override def getStateManager = stateManager
  override def getTimer = timer
  override def getViewPort = viewPort3D

  override def isPauseOnLostFocus = false

  override def setAppProfiler(p: AppProfiler) = {}
  override def setLostFocusBehavior(b: LostFocusBehavior) = {}
  override def setPauseOnLostFocus(b: Boolean) = {}
  override def setSettings(s: AppSettings) = settings = s
  override def setTimer(t: Timer) = timer = t

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
  override def stop(wait: Boolean) = context.destroy(wait)

  override def restart() = {}

  override def enqueue(r: Runnable) = throw Exception()
  override def enqueue[V](c: Callable[V]) = throw Exception()
