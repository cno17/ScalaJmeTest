package monkey.test.math

import com.jme3.math.Vector3f
import monkey.util.extension.MonkeyExt

object Test extends MonkeyExt:

  def main(args: Array[String]): Unit =
    val a = Vector3f()
    val b = Vector3f()
    a.addLocal(b, 5)
    
