package monkey.test.material

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial

object HoverTankTest extends SimpleApp:

  var geom: Geometry = null
  var light: PointLight = null
  var mat1: Material = null
  var mat2: Material = null

  override def init() =
    camera3D.setLocation(V(0, 0, 20))
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    val mat = LightMaterial(assetManager)
    mat.setDiffuseMap("Models/HoverTank/TankDiffuse.jpg")
    mat.setSpecularMap("Models/HoverTank/TankSpecular.jpg")
    geom = assetManager.loadModel("Models/HoverTank/Tank.mesh.xml").asInstanceOf[Node].getChild(0).asInstanceOf[Geometry]
    geom.createTangents()
    light = PointLight(V(0, 0, 10), C(1.0f, 1.0f, 1.0f), 50f)
    mat1 = geom.getMaterial
    mat2 = mat.delegate
    rootNode3D.addChild(geom)
    rootNode3D.addLight(light)
    keyListeners += handleKeyEvent

  def handleKeyEvent(e: KeyInputEvent) =
    val a = 0.1f
    if e.isPressed || e.isRepeating then
      if e.getKeyCode == KeyInput.KEY_UP then geom.rotate(-a, 0, 0)
      if e.getKeyCode == KeyInput.KEY_DOWN then geom.rotate(a, 0, 0)
      if e.getKeyCode == KeyInput.KEY_LEFT then geom.rotate(0, a, 0)
      if e.getKeyCode == KeyInput.KEY_RIGHT then geom.rotate(0, -a, 0)
      if e.getKeyCode == KeyInput.KEY_1 then geom.setMaterial(mat1)
      if e.getKeyCode == KeyInput.KEY_2 then geom.setMaterial(mat2)
