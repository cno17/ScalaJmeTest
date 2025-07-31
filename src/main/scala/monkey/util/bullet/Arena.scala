package monkey.util.bullet

import com.jme3.bullet.PhysicsSpace
import com.jme3.bullet.control.RigidBodyControl
import com.jme3.renderer.queue.RenderQueue.ShadowMode
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import monkey.util.extension.MonkeyExt
import monkey.util.material.SimpleMaterial

class Arena(m: SimpleMaterial, ps: PhysicsSpace) extends Node, MonkeyExt:

  def this(m: SimpleMaterial) = this(m, null)

  var floorSize = 100f
  var floorHeight = 1f
  var wallWidth = 1f
  var wallHeight = 1f

  var floorControll: RigidBodyControl = null
  var wallControllW: RigidBodyControl = null
  var wallControllE: RigidBodyControl = null
  var wallControllS: RigidBodyControl = null
  var wallControllN: RigidBodyControl = null

  var floorGeometry: Geometry = null
  var wallGeometryW: Geometry = null
  var wallGeometryE: Geometry = null
  var wallGeometryS: Geometry = null
  var wallGeometryN: Geometry = null

  def setFriction(f: Float) =
    floorControll.setFriction(f)
    wallControllW.setFriction(f)
    wallControllE.setFriction(f)
    wallControllS.setFriction(f)
    wallControllN.setFriction(f)

  def setRestitution(r: Float) =
    floorControll.setRestitution(r)
    wallControllW.setRestitution(r)
    wallControllE.setRestitution(r)
    wallControllS.setRestitution(r)
    wallControllN.setRestitution(r)
  
  def addTo(s: PhysicsSpace) =
    s.add(floorControll)
    s.add(wallControllW)
    s.add(wallControllE)
    s.add(wallControllS)
    s.add(wallControllN)

  def create() =
    createFloor()
    createWallW()
    createWallE()
    createWallS()
    createWallN()

  private def createFloor() =
    val mesh = Box(floorSize / 2, floorSize / 2, floorHeight / 2)
    floorControll = RigidBodyControl(0)
    floorGeometry = Geometry(mesh, m)
    floorGeometry.setLocalTranslation(0, 0, 0)
    floorGeometry.setShadowMode(ShadowMode.Receive)
    floorGeometry.addControl(floorControll)
    if ps != null then ps.add(floorControll)
    addChild(floorGeometry)

  // todo DRY
  
  private def createWallW() =
    val x = (wallWidth - floorSize) / 2
    val z = (floorHeight + wallHeight) / 2
    val mesh = Box(wallWidth / 2, floorSize / 2, wallHeight / 2)
    wallControllW = RigidBodyControl(0)
    wallGeometryW = Geometry(mesh, m)
    wallGeometryW.setLocalTranslation(x, 0, z)
    wallGeometryW.setShadowMode(ShadowMode.Receive)
    wallGeometryW.addControl(wallControllW)
    if ps != null then ps.add(wallControllW)
    addChild(wallGeometryW)

  private def createWallE() =
    val x = (floorSize - wallWidth) / 2
    val z = (floorHeight + wallHeight) / 2
    val mesh = Box(wallWidth / 2, floorSize / 2, wallHeight / 2)
    wallControllE = RigidBodyControl(0)
    wallGeometryE = Geometry(mesh, m)
    wallGeometryE.setLocalTranslation(x, 0, z)
    wallGeometryE.setShadowMode(ShadowMode.Receive)
    wallGeometryE.addControl(wallControllE)
    if ps != null then ps.add(wallControllE)
    addChild(wallGeometryE)

  private def createWallS() =
    val y = (wallWidth - floorSize) / 2
    val z = (floorHeight + wallHeight) / 2
    val mesh = Box((floorSize - 2 * wallWidth) / 2, wallWidth / 2, wallHeight / 2)
    wallControllS = RigidBodyControl(0)
    wallGeometryS = Geometry(mesh, m)
    wallGeometryS.setLocalTranslation(0, y, z)
    wallGeometryS.setShadowMode(ShadowMode.Receive)
    wallGeometryS.addControl(wallControllS)
    if ps != null then ps.add(wallControllS)
    addChild(wallGeometryS)

  private def createWallN() =
    val y = (floorSize - wallWidth) / 2
    val z = (floorHeight + wallHeight) / 2
    val mesh = Box((floorSize - 2 * wallWidth) / 2, wallWidth / 2, wallHeight / 2)
    wallControllN = RigidBodyControl(0)
    wallGeometryN = Geometry(mesh, m)
    wallGeometryN.setLocalTranslation(0, y, z)
    wallGeometryN.setShadowMode(ShadowMode.Receive)
    wallGeometryN.addControl(wallControllN)
    if ps != null then ps.add(wallControllN)
    addChild(wallGeometryN)

  override def clone() = null