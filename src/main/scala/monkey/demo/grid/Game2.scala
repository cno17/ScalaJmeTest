package monkey.demo.grid

import com.jme3.math.FastMath
import com.jme3.math.FastMath.PI
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp
import monkey.util.grid.Grid
import monkey.util.material.ColorMaterial

import scala.util.Random

object Game2 extends SimpleApp:

  var grid: Grid = null
  var car: Geometry = null

  override def init() =

    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.2f, 0.2f, 0.8f, 1.0f))
    grid = Grid(5, 5, 10, 10)
    car = Geometry(Box(2, 1, 3), mat)
    car.move(grid.tileExtX / 2, grid.tileExtY / 2, 1)
    grid.forEachTile(_.attachChild(geo()))
    grid.rotate(-FastMath.PI / 2, 0f, 0f)
    grid.centerTile.attachChild(car)
    rootNode3D.attachChild(grid)
    createObstacles(25)

  override def update(tpf: Float) =
    val dirZ = car.getLocalRotation().getRotationColumn(2)
    dirZ.scaleAdd(5f, car.getLocalTranslation())
    // cam.location.set(dirZ)
    // cam.lookAt(car.localTranslation, Vector3f(0f, 1f, 0f))
    camera3D.getLocation().set(0f, 100f, 100f)
    camera3D.lookAt(car.getLocalTranslation(), Vector3f(0f, 1f, 0f))

  def geo() =
    val ex = grid.tileExtX
    val ey = grid.tileExtY
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.2f, 0.8f, 0.2f, 1.0f))
    val geo = Geometry(Box(ex * 0.45f, ey * 0.45f, 0.1f), mat)
    geo.move(ex / 2, ey / 2, 0f)
    geo

  def createObstacles(n: Int) =
    val rnd = Random()
    for i <- 1 to n do
      val tx = rnd.nextFloat() * grid.tileExtX
      val ty = rnd.nextFloat() * grid.tileExtY
      val mat = ColorMaterial(assetManager)
      mat.setColor(C(0.8f, 0.2f, 0.2f, 1.0f))
      val geo = Geometry(Box(1f, 1f, 1f), mat)
      geo.move(tx, ty, 0.5f)
      grid.randomTile.attachChild(geo)
