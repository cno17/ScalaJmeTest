package monkey.demo.grid

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.FastMath.PI
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import monkey.util.application.old.MonkeyApp
import monkey.util.application.simple.SimpleApp
import monkey.util.grid.Grid
import monkey.util.material.ColorMaterial

object Game1 extends SimpleApp:

  val grid = Grid(33, 33, 1, 1)

  override def init() =

    // camera.setTranslation(...)
    // camera.lookFromTo
    camera3D.setTranslation(0, 50, 100)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    // cam.location.set(0f, 20f, 40f)
    // cam.lookAt(Vector3f(0f, 0f, 0f), Vector3f(0f, 1f, 0f))
    grid.forEachTile(_.attachChild(geo()))
    grid.rotateDeg(-90, 0, 0)
    rootNode3D.attachChild(grid)
    keyListeners += handleKeyEvent
  
  def handleKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_J then grid.scrollW()
      if e.getKeyCode() == KeyInput.KEY_L then grid.scrollE()
      if e.getKeyCode() == KeyInput.KEY_K then grid.scrollS()
      if e.getKeyCode() == KeyInput.KEY_I then grid.scrollN()

  def geo(): Geometry =
    val ex = grid.tileExtX
    val ey = grid.tileExtY
    val mat = ColorMaterial(assetManager)
    mat.setColor(ColorRGBA(0.2f, 0.8f, 0.2f, 1.0f))
    val geo = Geometry("Box", Box(ex * 0.25f, ey * 0.25f, 0.1f), mat, false)
    geo.move(ex / 2, ey / 2, 0f)
    // geo.setMaterial(mat)
    return geo
