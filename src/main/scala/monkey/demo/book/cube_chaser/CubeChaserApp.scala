package monkey.demo.book.cube_chaser

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.DirectionalLight
import com.jme3.math.Vector3f
import monkey.util.application.simple.SimpleApp

/**
 * This demo uses the simpleUpdate() loop to test repeatedly
 * if the camera is close to the white cube. 
 * If yes, we move the white cube away from the camera.
 */

// todo: camera controller ala FlyCam

object CubeChaserApp extends SimpleApp:

  val sceneExtent = 500f
  val cubeCount = 100
  val dir = Vector3f(1, 0, 0)
  var light: DirectionalLight = null


  override def init() =
    camera3D.moveTo(V(0, 0, 0))
    camera3D.lookAt(V(10, 0, 0), V(0, 0, 1))
    light = DirectionalLight(camera3D.getDirection)
    rootNode3D.addLight(light)
    Cube.init(assetManager)
    for i <- 1 to cubeCount do
      rootNode3D.addChild(Cube(sceneExtent))
    keyListeners += onKeyEvent

  //  override def update(tpf: Float) =
  //    if camera3D.getLocation.distance(scaredCube.getLocalTranslation) < 10 then
  //      scaredCube.move(camera3D.getDirection)

  def onKeyEvent(e: KeyInputEvent) =
    val a = 0.03f
    val s = 2.00f
    camera3D.getDirection(dir)
    light.setDirection(dir) // necessary?
    if e.isPressed || e.isRepeating then
      if e.getKeyCode == KeyInput.KEY_UP then camera3D.translate(dir.multLocal(+s))
      if e.getKeyCode == KeyInput.KEY_DOWN then camera3D.translate(dir.multLocal(-s))
      if e.getKeyCode == KeyInput.KEY_LEFT then camera3D.rotateY(+a)
      if e.getKeyCode == KeyInput.KEY_RIGHT then camera3D.rotateY(-a)

