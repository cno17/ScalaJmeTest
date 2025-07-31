package monkey.test.renderer

import com.jme3.renderer.Camera
import monkey.util.extension.MonkeyExt

object Test extends MonkeyExt:

  def main(args: Array[String]) =
    val c = Camera(400, 300)
    println(c.getLeft())
    println(c.getUp())
    println(c.getDirection())
