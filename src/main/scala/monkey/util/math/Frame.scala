package monkey.util.math

import com.jme3.math.Vector3f
import monkey.util.extension.math.MathExt
import monkey.util.extension.math.VectorExt

// object Frame:

  
class Frame(dirX: Vector3f, dirY: Vector3f, dirZ: Vector3f, val org: Vector3f) extends Basis(dirX, dirY, dirZ):
  
  def this() = this(Vector3f(1, 0, 0), Vector3f(0, 1, 0), Vector3f(0, 0, 1), Vector3f(0, 0, 0))

//   var dirX = v(1, 0, 0)
//   var dirY = v(0, 1, 0)
//   var dirZ = v(0, 0, 1)
//   var orig = v(0, 0, 0)
