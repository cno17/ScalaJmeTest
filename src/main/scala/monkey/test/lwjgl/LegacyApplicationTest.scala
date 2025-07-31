package monkey.test.lwjgl

import com.jme3.app.LegacyApplication

object LegacyApplicationTest extends LegacyApplication:

  override def initialize() =
    super.initialize()
    println(getContext.isRenderable)
    println(keyInput.isInitialized)

  def main(args: Array[String]) =
    start(true)
    stop(true)
    println("ok")

