package monkey.demo.game

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.DirectionalLight
import com.jme3.light.PointLight
import com.jme3.light.SpotLight
import com.jme3.math.ColorRGBA
import com.jme3.math.FastMath
import com.jme3.math.Vector3f
import com.jme3.scene.Spatial
import com.jme3.scene.shape.Box
import com.jme3.texture.Texture2D
import monkey.util.application.old.MonkeyApp
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial
import monkey.util.material.SimpleMaterial

// todo: wrap your head around this ...

object Game2 extends SimpleApp:

  var dLight: DirectionalLight = null
  var pLight: PointLight = null
  var sLight: SpotLight = null
  // var cameraController: PlanarCameraController = null

  override def init() =
    createScene(500, 500, 1000)
    dLight = DirectionalLight(V(0, 0, -1))
    pLight = PointLight(V(0, 0, 100))
    rootNode3D.addLight(pLight)
    camera3D.setTranslation(10, 0, 2)
    camera3D.lookAt(V(0, 0, 2), V(0, 0, 1))
    // pLight.setPosition(cam.getTranslation)
    // cameraController = PlanarCameraController(this)

  override def update(tpf: Float) =
    pLight.setPosition(camera3D.getTranslation)

  def createScene(rx: Int, ry: Int, no: Int) =
    val mat1 = LightMaterial(assetManager)
    val mat2 = LightMaterial(assetManager)
    mat1.setDiffuseColor(ColorRGBA.Gray)
    mat2.setDiffuseColor(ColorRGBA.Green)
    val floor = Geometry(Box(rx, ry, 1), mat1)
    rootNode3D.addChild(floor)
    for i <- 1 to no do
      val tx = FastMath.nextRandomInt(-rx, rx)
      val ty = FastMath.nextRandomInt(-ry, ry)
      val obstacle = Geometry(Box(1, 1, 1), mat2)
      obstacle.translate(tx, ty, 2)
      rootNode3D.addChild(obstacle)

