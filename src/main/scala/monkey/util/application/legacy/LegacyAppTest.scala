package monkey.util.application.legacy

import monkey.util.extension.MonkeyExt

object LegacyAppTest extends LegacyApp, MonkeyExt:

  override def initialize() =
    super.initialize()
    println(context.isRenderable)
    println(mouseInput.isInitialized)
    println(keyInput.isInitialized)

  def main(args: Array[String]) =
    start()
    stop()
    println("ok")

