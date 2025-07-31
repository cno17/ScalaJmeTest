package monkey.util.application.simple

import com.jme3.system.JmeSystem
import com.jme3.system.SystemListener

trait SystemListenerImpl extends SystemListener:

  this: SimpleApp =>

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

  override def handleError(msg: String, t: Throwable) =
    JmeSystem.handleErrorMessage(msg + "\n" + t.getClass.getSimpleName + ": " + t.getMessage)
    stop()

