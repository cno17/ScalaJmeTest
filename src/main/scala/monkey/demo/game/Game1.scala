package monkey.demo.game

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.PointLight
import com.jme3.light.SpotLight
import com.jme3.math.Vector3f
import com.jme3.scene.Spatial
import com.jme3.scene.shape.Box
import com.jme3.texture.Texture2D
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial
import monkey.util.material.SimpleMaterial

// todo: wrap your head around this ...

object Game1 extends SimpleApp:

  var floor: Spatial = null
  var pLight: PointLight = null
  var sLight: SpotLight = null
  // var cameraController: PlanarCameraController = null

  override def init() =
    pLight = PointLight(V(0, 10, 10))
    val mat = LightMaterial(assetManager)
    mat.setDiffuseMap(assetManager.loadTexture("Images/Bird1.jpg").asInstanceOf[Texture2D])
    floor = createFloor(mat)
    rootNode3D.addChild(floor)
    rootNode3D.addLight(pLight)
    camera3D.setTranslation(10, 0, 2)
    camera3D.lookAt(V(0, 0, 2), V(0, 0, 1))
    pLight.setPosition(camera3D.getTranslation)
    // cameraController = PlanarCameraController(this)

  // override def simpleUpdate(dt: Float) =
    // floor.rotate(0, 0, 0.002f)

  def createFloor(mat: SimpleMaterial) =
    val res = Geometry(Box(5, 5, 0.1f), mat)
    res
