package monkey.demo.grid

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.scene.Geometry
import com.jme3.terrain.heightmap.HillHeightMap
import monkey.util.application.simple.SimpleApp
import monkey.util.grid.Grid
import monkey.util.terrain.TerrainMesh

object TerrainGridDemo1 extends SimpleApp:

  val terrainSize = 4096
  val tileCount = 64
  val tileSize = terrainSize / tileCount
  var mat: Material = null
  var grid: Grid = null
  var light: PointLight = null

  override def init() =

    camera3D.setFrustumFar(10000f)
    camera3D.setTranslation(0, terrainSize, terrainSize * 2)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    mat = Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    mat.setColor("Diffuse", C(0.2f, 0.8f, 0.2f))
    mat.setColor("Specular", C(0.8f, 0.8f, 0.8f))
    mat.setFloat("Shininess", 3f)
    // mat.setBoolean("UseMaterialColors", true)
    // mat.setBoolean("VertexLighting", true);
    // mat.getAdditionalRenderState().setWireframe(true)
    grid = createGrid()
    grid.rotateDeg(-90, 0, 0)
    light = PointLight()
    light.setPosition(V(0f, 20f, 20f))
    light.setPosition(V(0, terrainSize, terrainSize))
    light.setColor(C(1f, 1f, 1f))
    rootNode3D.attachChild(grid)
    rootNode3D.addLight(light)
    keyListeners += onKeyEvent

  def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_J then grid.scrollW()
      if e.getKeyCode() == KeyInput.KEY_L then grid.scrollE()
      if e.getKeyCode() == KeyInput.KEY_K then grid.scrollS()
      if e.getKeyCode() == KeyInput.KEY_I then grid.scrollN()

  def createGrid() =
    val minR = terrainSize / 20
    val maxR = terrainSize / 10
    val map = HillHeightMap(terrainSize, 1000, minR, maxR)
    map.normalize(0.1f * terrainSize)
    val maps = map.split(tileCount)
    val grid = Grid(tileCount, tileCount, tileSize, tileSize)
    for i <- 0 to tileCount - 1 do
      for j <- 0 to tileCount - 1 do
        val mesh = TerrainMesh(maps(i)(j))
        val geom = new Geometry("", mesh)
        geom.setMaterial(mat)
        grid.tiles(i)(j).attachChild(geom)
    grid
