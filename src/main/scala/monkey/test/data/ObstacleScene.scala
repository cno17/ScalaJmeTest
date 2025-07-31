package monkey.test.data

import com.jme3.asset.AssetManager
import com.jme3.math.ColorRGBA
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import com.jme3.scene.shape.Cylinder
import monkey.util.extension.MonkeyExt
import monkey.util.material.ColorMaterial
import monkey.util.math.Rnd

class ObstacleScene(am: AssetManager) extends Node, Rnd:

  var floorRadiusX = 100f
  var floorRadiusY = 100f
  var minObstacleRadius = 2f
  var maxObstacleRadius = 5f
  var boxCount = 50
  var cylinderCount = 20

  def initialize() =
    val frx = floorRadiusX
    val fry = floorRadiusY
    val or1 = minObstacleRadius
    val or2 = maxObstacleRadius
    val floorMaterial = ColorMaterial(am)
    floorMaterial.setColor(ColorRGBA.Gray)
    val boxMaterial = ColorMaterial(am)
    boxMaterial.setColor(ColorRGBA.Blue)
    boxMaterial.setWireframe(true)
    val cylinderMaterial = ColorMaterial(am)
    cylinderMaterial.setColor(ColorRGBA.Green)
    cylinderMaterial.setWireframe(true)
    val floor = Geometry("Floor", Box(frx, fry, 1f))
    floor.setMaterial(floorMaterial.delegate)
    attachChild(floor)
    for i <- 1 to boxCount do
      val box = Geometry("Box", Box(rndF(or1, or2), rndF(or1, or2), 2))
      box.setLocalTranslation(rndF(-frx + or2, frx - or2), rndF(-fry + or2, fry - or2), 2)
      box.rotate(0, 0, rndF(0, 6.28f))
      box.setMaterial(boxMaterial.delegate)
      attachChild(box)
    for i <- 1 to cylinderCount do
      val cylinder = Geometry("Cylinder", Cylinder(8, 8, rndF(or1, or2), 2f, true))
      cylinder.setLocalTranslation(rndF(-frx, frx), rndF(-fry, fry), 2)
      cylinder.setMaterial(cylinderMaterial.delegate)
      attachChild(cylinder)


  override def clone() = null
