package monkey.demo.grid

import com.jme3.asset.AssetManager
import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.Vector2f
import com.jme3.scene.shape.Box
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import monkey.util.grid.Tile
import monkey.util.input.RawInputAdapter

class Car(val tile: Tile, am: AssetManager) extends Node, RawInputAdapter:

  val vel = Vector2f()
  val acc = Vector2f()

  def init() =
    val mat = Material(am, "Common/MatDefs/Misc/Unshaded.j3md")
    mat.setColor("Color", ColorRGBA(0.2f, 0.2f, 0.8f, 1.0f))
    val geo = Geometry("Box", Box(3f, 3f, 0.5f))
    geo.setMaterial(mat)
    // geo.material = mat // ???
    move(tile.extentX / 2, tile.extentY / 2, 1)
    tile.attachChild(this)

  override def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      val g = tile.grid
      if e.getKeyCode() == KeyInput.KEY_J then g.scrollW()
      if e.getKeyCode() == KeyInput.KEY_L then g.scrollE()
      if e.getKeyCode() == KeyInput.KEY_K then g.scrollS()
      if e.getKeyCode() == KeyInput.KEY_I then g.scrollN()

  override def clone() = this
