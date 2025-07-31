package monkey.demo.robot

import com.jme3.input.event.MouseMotionEvent
import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp
import monkey.util.robot.RoundedPart

object RoundedPartTest extends SimpleApp:

  var material: Material = null
  var part: RoundedPart = null
  var light: PointLight = null

  override def init() =
    camera3D.setTranslation(0, 10, 50)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    // material = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    // material.setColor("Color", C(0.2f, 0.8f, 0.2f))
    // material.getAdditionalRenderState().setWireframe(true)
    material = Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    material.setBoolean("UseMaterialColors", true)
    material.setColor("Diffuse", C(0.2f, 0.8f, 0.2f))
    material.setColor("Specular", C(0.8f, 0.8f, 0.8f))
    material.setFloat("Shininess", 3f)
    // material.setBoolean("VertexLighting", true);
    // material.getAdditionalRenderState().setWireframe(true)

    part = RoundedPart(4.0f, 5.0f, 1.2f, 64, material)

    light = PointLight()
    light.setPosition(V(0f, 20f, 20f))
    light.setColor(C(1f, 1f, 1f))
    // light.setRadius(50f)

    val box = new Geometry("Box", Box(1, 2, 3))
    box.setMaterial(material)

    rootNode3D.attachChild(part)
    // rootNode.attachChild(box)
    rootNode3D.addLight(light)
    mouseMotionListeners += onMouseMotionEvent

  // override def simpleUpdate(tpf: Float) = rootNode.rotate(0.002f, 0.00f, 0.00f)

  def onMouseMotionEvent(e: MouseMotionEvent) =
    val dx = e.getDX() * 0.01f
    val dy = e.getDY() * 0.01f
    rootNode3D.rotate(dy, dx, 0)
