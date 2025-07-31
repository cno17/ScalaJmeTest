package monkey.test.math

import com.jme3.math.Transform
import monkey.util.extension.MonkeyExt

object TransformTest extends MonkeyExt:

  def main(args: Array[String]) =
    val t1 = Transform()
    t1.setTranslation(2, 3, 5)
    println(t1.toMatrix().str)
