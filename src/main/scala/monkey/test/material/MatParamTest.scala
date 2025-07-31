package monkey.test.material

import com.jme3.material.MatParam
import com.jme3.math.Vector3f
import com.jme3.shader.VarType

object MatParamTest:

  def main(args: Array[String]) =
    val p = MatParam(VarType.Vector3, "pos", Vector3f())
    p.getValue.asInstanceOf[Vector3f].set(2, 3, 5)
    println(p)
