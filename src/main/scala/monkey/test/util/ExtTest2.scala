package monkey.test.util

import com.jme3.light.Light
import com.jme3.light.PointLight
import com.jme3.scene.Node
import com.jme3.scene.Spatial
import com.jme3.scene.control.Control

object ExtTest2:

  extension (s: Spatial)
    def +=(c: Control) = s.addControl(c)
    def +=(l: Light) = s.addLight(l)

  extension (n: Node)
    def +=(s: Spatial) = n.attachChild(s)

  def main(args: Array[String]) =
    val n = Node()
    val l = PointLight()
    n += Node()
    // n += PointLight() // ???
    println()
