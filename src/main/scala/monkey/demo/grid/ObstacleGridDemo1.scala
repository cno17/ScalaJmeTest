package monkey.demo.grid

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.PointLight
import com.jme3.scene.shape.Box
import com.jme3.scene.shape.Cylinder
import monkey.util.application.simple.SimpleApp
import monkey.util.grid.Grid
import monkey.util.material.LightMaterial

import scala.util.Random

object ObstacleGridDemo1 extends SimpleApp:

  val tileCount = 51
  val tileSize = 10
  val obstacleCount = 50
  val obstacleMinRadius = 2
  val obstacleMaxRadius = 5

  var grid: Grid = null
  var light: PointLight = null

  override def init() =
    grid = createGrid()
    light = createLight()
    createObstacles()
    rootNode3D.attachChild(grid)
    rootNode3D.addLight(light)
    camera3D.setFrustumFar(10000f)
    camera3D.setTranslation(0, grid.extentX, grid.extentX * 2)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    keyListeners += onKeyEvent

  def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_J then grid.scrollW()
      if e.getKeyCode() == KeyInput.KEY_L then grid.scrollE()
      if e.getKeyCode() == KeyInput.KEY_K then grid.scrollS()
      if e.getKeyCode() == KeyInput.KEY_I then grid.scrollN()

  def createGrid() =
    val mat = createMaterial(0.2f, 0.8f, 0.2f, 3.0f)
    val box = Box(tileSize * 0.45f, tileSize * 0.45f, 1)
    val grid = Grid(tileCount, tileCount, tileSize, tileSize)
    for i <- 0 to tileCount - 1 do
      for j <- 0 to tileCount - 1 do
        val tile = grid.tiles(i)(j)
        val geom = Geometry(box, mat) // shared geom possible?
        geom.setMaterial(mat)
        geom.translate(0, 0, -1)
        tile.attachChild(geom)
    grid.rotateDeg(-90, 0, 0)
    grid

  def createLight() =
    val light = PointLight()
    light.setPosition(V(grid.centerX, grid.centerY, 20))
    light.setColor(C(1f, 1f, 1f))
    light

  def createObstacles() =
    val mat = createMaterial(0.8f, 0.2f, 0.2f, 3.0f)
    val r1 = obstacleMinRadius
    val r2 = obstacleMaxRadius
    for i <- 1 to obstacleCount do
      val r = r1 + (r2 - r1) * Random.nextFloat()
      val h = 15.0f
      val x = tileSize * Random.nextFloat()
      val y = tileSize * Random.nextFloat()
      val mesh = Cylinder(2, 16, r, h, true)
      val geom = Geometry(mesh, mat)
      geom.translate(x, y, h * 0.5f)
      grid.randomTile.attachChild(geom)

  // SimpleMaterial!    
  def createMaterial(r: Float, g: Float, b: Float, sh: Float) =
    val mat = LightMaterial(assetManager)
    mat.setDiffuseColor(C(r, g, b))
    mat.setSpecularColor(C(0.8f, 0.8f, 0.8f))
    mat.setShininess(sh)
    mat.useMaterialColors(true)
    // mat.setBoolean("VertexLighting", true);
    // mat.getAdditionalRenderState().setWireframe(true)
    mat
