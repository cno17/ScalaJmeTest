package monkey.test.lwjgl

import com.jme3.app.SimpleApplication

object SimpleApplicationTest extends SimpleApplication:

  override def simpleInitApp() =
    println(getContext.isRenderable)
    println(keyInput.isInitialized)

  def main(args: Array[String]) =
    start(true)
    stop(true)
    println("ok")

