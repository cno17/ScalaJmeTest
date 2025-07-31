package monkey.test.application

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.PointLight
import com.jme3.scene.Node
import com.jme3.scene.shape.Cylinder
import com.jme3.scene.shape.Torus
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial

object WheelTest extends SimpleApp:

  // todo lod levels!

  val hubRadius = 0.9f
  val tyreRadius = 1.2f
  var pivot: Node = null
  var light: PointLight = null

  override def init() =
    val matCyl = LightMaterial(assetManager)
    val matTor = LightMaterial(assetManager)
    matCyl.setDiffuseColor(C(0.8f, 0.8f, 0.8f))
    matTor.setDiffuseMap("Images/Metals/Metal2Diffuse.png")
    matTor.setSpecularMap("Images/Metals/Metal2Specular.png")
    matTor.setNormalMap("Images/Metals/Metal2Normal.png")
    val geoCyl = Geometry(Cylinder(2, 32, hubRadius, tyreRadius, true), matCyl, false)
    val geoTor = Geometry(Torus(32, 16, tyreRadius, hubRadius + tyreRadius), matTor, true)
    pivot = Node("pivot")
    pivot.attachChild(geoCyl)
    pivot.attachChild(geoTor)
    pivot.rotate(0.4f, 0.4f, 0.0f)
    light = PointLight(V(0, 0, 10), C(1.0f, 1.0f, 1.0f), 50f)
    rootNode3D.attachChild(pivot)
    rootNode3D.addLight(light)
    keyListeners += handleKeyEvent

  def handleKeyEvent(e: KeyInputEvent) =
    val a = 0.1f
    if e.isPressed || e.isRepeating then
      if e.getKeyCode == KeyInput.KEY_UP then pivot.rotate(-a, 0, 0)
      if e.getKeyCode == KeyInput.KEY_DOWN then pivot.rotate(a, 0, 0)
      if e.getKeyCode == KeyInput.KEY_LEFT then pivot.rotate(0, a, 0)
      if e.getKeyCode == KeyInput.KEY_RIGHT then pivot.rotate(0, -a, 0)
