package monkey.test.bounding

import com.jme3.bounding.BoundingBox
import monkey.util.extension.math.VectorExt

object BoundingBoxTest extends VectorExt:

  def main(args: Array[String]) =
    val b = BoundingBox(V(-1, -1, -1), V(1, 2, 3))
    println(b.getXExtent)
    println(b.getYExtent)
    println(b.getZExtent)
