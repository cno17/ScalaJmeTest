package monkey.demo.bullet

import com.jme3.bullet.BulletAppState
import com.jme3.bullet.PhysicsSpace
import com.jme3.bullet.control.RigidBodyControl
import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.DirectionalLight
import com.jme3.math.ColorRGBA
import com.jme3.math.Quaternion
import com.jme3.post.FilterPostProcessor
import com.jme3.renderer.queue.RenderQueue.ShadowMode
import com.jme3.scene.Mesh
import com.jme3.scene.shape.Box
import com.jme3.scene.shape.Cylinder
import com.jme3.scene.shape.Sphere
import com.jme3.shadow.DirectionalLightShadowFilter
import monkey.util.application.simple.SimpleApp
import monkey.util.bullet.Arena
import monkey.util.material.LightMaterial
import monkey.util.math.Rnd

// todo: startRotation
// todo: z = up
// todo: remove bodies under floor, height = z, point light attenuation, friction
// todo: shadows, walls or oval arena

object FallingBodies04 extends SimpleApp, Rnd:

  val floorSize = 100f
  val floorHeight = 1f
  val wallWidth = 1f
  val wallHeight = 1f
  val minBodyRadius = 1f
  val maxBodyRadius = 5f

  val boxFrequency = 40
  val sphereFrequency = 30
  val cylinderFrequency = 30

  var physicsSpace: PhysicsSpace = null
  var arenaMaterial: LightMaterial = null
  var boxMaterial: LightMaterial = null
  var sphereMaterial: LightMaterial = null
  var cylinderMaterial: LightMaterial = null
  var arena: Arena = null
  var light: DirectionalLight = null

  var friction = 0.5f
  var restitution = 0.5f

  var tick = 0

  override def init() =
    assert(boxFrequency + sphereFrequency + cylinderFrequency == 100)
    camera3D.moveTo(V(0, -floorSize * 1.8f, floorSize * 0.5f))
    camera3D.lookAt(V(0, 0, 0), V(0, 0, 1))
    val state = BulletAppState()
    state.setDebugEnabled(false)
    state.setSpeed(1f)
    stateManager.attach(state)
    physicsSpace = state.getPhysicsSpace
    physicsSpace.setGravity(V(0, 0, -10))
    arenaMaterial = createMaterial(0.8f, 0.8f, 0.8f)
    boxMaterial = createMaterial(0.8f, 0.2f, 0.2f)
    sphereMaterial = createMaterial(0.2f, 0.8f, 0.2f)
    cylinderMaterial= createMaterial(0.2f, 0.2f, 0.8f)
    arena = createArena()
    light = createLight()
    rootNode3D.addChild(arena)
    rootNode3D.addLight(light)
    // todo: extension methods!
    val filter = DirectionalLightShadowFilter(assetManager, 1024, 2)
    filter.setLight(light)
    filter.setEnabled(true)
    val processor = FilterPostProcessor(assetManager)
    processor.addFilter(filter)
    viewPort3D.addProcessor(processor)
    keyListeners += onKeyEvent

  override def update(tpf: Float) =
    tick += 1
    if tick % 100 == 0 then
      val r = rndI(0, 100)
      if r < boxFrequency then addBox()
      else if r < boxFrequency + sphereFrequency then addSphere()
      else addCylinder()

  private def createMaterial(r: Float, g: Float, b: Float) =
    val mat = LightMaterial(assetManager)
    mat.useMaterialColors(true)
    mat.setDiffuseColor(C(r, g, b))
    mat.setSpecularColor(C(1f, 1f, 1f))
    mat.setShininess(45)
    mat

  private def createArena() =
    val res = Arena(arenaMaterial, physicsSpace)
    res.floorSize = floorSize
    res.floorHeight = floorHeight
    res.wallWidth = wallWidth
    res.wallHeight = wallHeight
    res.create()
    res.setFriction(friction)
    res.setRestitution(restitution)
    res

  private def createLight() =
    val light = DirectionalLight()
    light.setColor(ColorRGBA.White)
    light.setDirection(V(0, 1, -1).normalize())
    light

  // DRY

  def addBox() =
    val r1 = minBodyRadius
    val r2 = maxBodyRadius
    val rx = rndF(r1, r2)
    val ry = rndF(r1, r2)
    val rz = rndF(r1, r2)
    val mesh = Box(rx, ry, rz)
    val control = RigidBodyControl(rx * ry * rz * 8)
    val geometry = Geometry(mesh, boxMaterial)
    geometry.setLocalTranslation(newStartTra())
    geometry.setLocalRotation(newStartRot())
    geometry.setShadowMode(ShadowMode.CastAndReceive)
    geometry.addControl(control)
    control.setFriction(friction)
    control.setRestitution(restitution)
    physicsSpace.add(control)
    rootNode3D.addChild(geometry)

  def addSphere() =
    val r1 = minBodyRadius
    val r2 = maxBodyRadius
    val r = rndF(r1, r2)
    val v = 3.14f * 4 / 3 * r * r * r
    val mesh = Sphere(16, 16, r)
    val control = RigidBodyControl(v)
    val geometry = Geometry(mesh, sphereMaterial)
    geometry.setLocalTranslation(newStartTra())
    geometry.setLocalRotation(newStartRot())
    geometry.setShadowMode(ShadowMode.CastAndReceive)
    geometry.addControl(control)
    control.setFriction(friction)
    control.setRestitution(restitution)
    physicsSpace.add(control)
    rootNode3D.addChild(geometry)

  def addCylinder() =
    val r1 = minBodyRadius
    val r2 = maxBodyRadius
    val r = rndF(r1, r2)
    val h = rndF(r1, r2)
    val v = 3.14f * r * r * h
    val mesh = Cylinder(16, 16, r, h, true)
    val control = RigidBodyControl(v)
    val geometry = Geometry(mesh, cylinderMaterial)
    geometry.setLocalTranslation(newStartTra())
    geometry.setLocalRotation(newStartRot())
    geometry.setShadowMode(ShadowMode.CastAndReceive)
    geometry.addControl(control)
    control.setFriction(friction)
    control.setRestitution(restitution)
    physicsSpace.add(control)
    rootNode3D.addChild(geometry)

  def newStartTra() =
    val f = floorSize / 4
    val x = rndF(-f, f)
    val y = rndF(-f, f)
    val z = rndF(3 * f, 9 * f)
    V(x, y, z)

  def newStartRot() =
    val a = rndF(0, 6.28f)
    val b = rndF(0, 6.28f)
    val c = rndF(0, 6.28f)
    Quaternion().fromAngles(a, b, c)

  def newMesh() = 0

  def getVolume(m: Mesh) = 0

// ext Mesh.getVolume?

  def onKeyEvent(e: KeyInputEvent) =
    val a = 0.05f
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_LEFT then rootNode3D.rotate(0, 0, -a)
      if e.getKeyCode() == KeyInput.KEY_RIGHT then rootNode3D.rotate(0, 0, +a)
      if e.getKeyCode() == KeyInput.KEY_DOWN then rootNode3D.rotate(+a, 0, 0)
      if e.getKeyCode() == KeyInput.KEY_UP then rootNode3D.rotate(-a, 0, 0)
