package monkey.test.renderer

import monkey.util.application.simple.SimpleApp

object RendererTest extends SimpleApp:

  override def init() =
    val caps = renderer.getCaps()
    println(caps)
