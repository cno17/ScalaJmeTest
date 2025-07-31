package monkey.test.application

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.math.Vector3f
import monkey.test.data.ObstacleScene
import monkey.util.application.simple.SimpleApp
import monkey.util.vehicle.KineticCar

object ObstacleSceneTest extends SimpleApp:

  var scene: ObstacleScene = null
  var car: KineticCar = null
  var dir = Vector3f()

  override def init() =
    scene = ObstacleScene(assetManager)
    scene.initialize()
    car = KineticCar(assetManager)
    car.initialize()
    rootNode3D.attachChild(scene)
    rootNode3D.attachChild(car)
    // camera.setFrame(V(0, 0, 1), V(1, 0, 0), V(0, 1, 0), V(0, 0, 1))
    // camera.rotateX(-1.57f)
    camera3D.setTranslation(0, 0, 1)
    camera3D.lookAt(V(10, 0, 1), V(0, 0, 1))
    // camera.setRotation(V(-1, 0, 0), V(0, 1, 0), V(0, 0, -1))
    keyListeners += handleKeyEvent

  def handleKeyEvent(e: KeyInputEvent) =
    val a = 0.03f
    val s = 2.00f
    camera3D.getDirection(dir)
    if e.isPressed || e.isRepeating then
      if e.getKeyCode == KeyInput.KEY_UP then camera3D.translate(dir.multLocal(+s))
      if e.getKeyCode == KeyInput.KEY_DOWN then camera3D.translate(dir.multLocal(-s))
      if e.getKeyCode == KeyInput.KEY_LEFT then camera3D.rotateY(+a)
      if e.getKeyCode == KeyInput.KEY_RIGHT then camera3D.rotateY(-a)

