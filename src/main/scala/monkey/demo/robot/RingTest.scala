package monkey.demo.robot

import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp
import monkey.util.robot.Ring

object RingTest extends SimpleApp:

  var material: Material = null
  var ring: Ring = null
  var light: PointLight = null

  override def init() =
    camera3D.setTranslation(0, 10, 50)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))

    material = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    material.setColor("Color", ColorRGBA.Blue)
    material.getAdditionalRenderState().setWireframe(true)
    // material = Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    // material.setBoolean("UseMaterialColors", true)
    // material.setColor("Diffuse", ColorRGBA.Red)
    // material.setFloat("Shininess", 3f)
    // mat2.setBoolean("VertexLighting", true);

    ring = Ring(material)
    ring.rad1 = 4
    ring.rad2 = 8
    ring.numS = 12
    ring.init()
    ring.geomTop.setMaterial(material)

    light = PointLight()
    light.setPosition(V(0f, 10f, 0f))
    light.setColor(C(1f, 1f, 1f))
    light.setRadius(50f)

    val box = new Geometry("Box", Box(1, 2, 3))
    box.setMaterial(material)

    rootNode3D.attachChild(ring)
// rootNode.attachChild(box)
// rootNode.addLight(light)

// override def simpleUpdate(tpf: Float) = rootNode.rotate(0.001f, 0.002f, 0.003f)
    
