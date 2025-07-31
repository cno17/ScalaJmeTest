package monkey.util.application.simple

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.system.SystemListener
import monkey.util.application.App
import monkey.util.application.VideoComponent

trait SimpleApp extends App, VideoComponent, SystemListener:

  // App
  
  override def initApp() =
    initInput()
    initVideo()
    init()

  override def updateApp(tpf: Float) =
    updateInput(tpf)
    stateManager.update(tpf) // needs work
    update(tpf)
    // move to updateVideo()?
    stateManager.render(renderManager)
    render(renderManager)
    stateManager.postRender()
    updateVideo(tpf)

  override def exitApp() =
    exit()
    exitInput()
    stateManager.cleanup() // needs work

  // SystemListener

  override def reshape(w: Int, h: Int): Unit =
    if renderManager != null then renderManager.notifyReshape(w, h)

  override def rescale(x: Float, y: Float): Unit =
    if renderManager != null then renderManager.notifyRescale(x, y)


  // todo!
  private def onEscape(e: KeyInputEvent) =
    if e.getKeyCode == KeyInput.KEY_ESCAPE then requestClose(true)
