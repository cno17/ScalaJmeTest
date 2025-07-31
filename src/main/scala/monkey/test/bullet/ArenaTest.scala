package monkey.test.bullet

import com.jme3.bullet.BulletAppState
import com.jme3.bullet.PhysicsSpace
import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.DirectionalLight
import com.jme3.math.ColorRGBA
import monkey.util.application.simple.SimpleApp
import monkey.util.bullet.Arena
import monkey.util.material.LightMaterial

object ArenaTest extends SimpleApp:

  var physicsSpace: PhysicsSpace = null
  var material: LightMaterial = null
  var light: DirectionalLight = null
  var arena: Arena = null

  override def init() =
    camera3D.moveTo(V(0, -200, 100))
    camera3D.lookAt(V(0, 0, 0), V(0, 0, 1))
    val state = BulletAppState()
    stateManager.attach(state)
    physicsSpace = state.getPhysicsSpace
    material = createMaterial()
    light = createLight()
    arena = Arena(material, physicsSpace)
    arena.create()
    rootNode3D.addChild(arena)
    rootNode3D.addLight(light)
    keyListeners += onKeyEvent

  private def createMaterial() =
    val res = LightMaterial(assetManager)
    res.useMaterialColors(true)
    res.setAmbientColor(ColorRGBA.White)
    res.setDiffuseColor(ColorRGBA.White)
    res.setSpecularColor(ColorRGBA.White)
    res.setShininess(45)
    res

  private def createLight() =
    val res = DirectionalLight()
    res.setColor(ColorRGBA.White)
    res.setDirection(V(0, 1, -1).normalize())
    res

  def onKeyEvent(e: KeyInputEvent) =
    val a = 0.05f
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_LEFT then arena.rotate(0, 0, -a)
      if e.getKeyCode() == KeyInput.KEY_RIGHT then arena.rotate(0, 0, +a)
      if e.getKeyCode() == KeyInput.KEY_DOWN then arena.rotate(+a, 0, 0)
      if e.getKeyCode() == KeyInput.KEY_UP then arena.rotate(-a, 0, 0)
