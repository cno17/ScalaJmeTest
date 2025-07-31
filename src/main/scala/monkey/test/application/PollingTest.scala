package monkey.test.application

import monkey.util.application.simple.SimpleApp
import monkey.util.lwjgl.glfw.GlfwMouseButton

object PollingTest extends SimpleApp:

  override def update(tpf: Float) =
    val lbd = isMouseButtonDown(GlfwMouseButton.Left)
    val skd = isShiftKeyDown
    println(s"$lbd, $skd")
