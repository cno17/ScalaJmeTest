package monkey.test.util

import com.jme3.app.SimpleApplication
import com.jme3.light.Light
import com.jme3.light.PointLight
import com.jme3.scene.Node
import com.jme3.scene.Spatial
import com.jme3.scene.control.Control

object ExtTest extends SimpleApplication:

  extension (n: Node)
    def +=(c: Control) = n.addControl(c)
    def +=(l: Light) = n.addLight(l)
    def +=(s: Spatial) = n.attachChild(s)

  override def simpleInitApp() =
    rootNode += PointLight()
    rootNode += Node()
    println(2)

  def main(args: Array[String]) =
    start()
    

