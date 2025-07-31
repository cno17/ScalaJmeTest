package monkey.demo.scenegraph

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import com.jme3.scene.shape.Line
import monkey.util.application.old.MonkeyApp
import monkey.util.application.simple.SimpleApp
import monkey.util.material.ColorMaterial

/**
 * Scene Graph for Dummies:
 * - translate
 * - rotate local / global
 * - parent, child or sun, earth, moon
 */

object Demo1 extends SimpleApp:

  val cellCount = 20
  val cellSize = 1f

  var grid: Node = null
  var box: Geometry = null

  override def init() =

    camera3D.setTranslation(0, 0, 50)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    grid = newGrid()
    box = newBox()
    rootNode3D.addChild(grid)
    rootNode3D.addChild(box)
    keyListeners += onKeyEvent

  def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_J then println(2)

  def newGrid() =
    val res = Node()
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.2f, 0.2f, 0.2f))
    val a = 0f
    val b = cellCount * cellSize
    for i <- 0 to cellCount do
      val x = i * cellSize
      val line = Line(V(x, a, 0), V(x, b, 0))
      res.addChild(Geometry(line, mat))
    for j <- 0 to cellCount do
      val y = j * cellSize
      val line = Line(V(a, y, 0), V(b, y, 0))
      res.addChild(Geometry(line, mat))
    val center = res.getWorldBound.getCenter
    res.translate(center * -1)
    res

  def newBox() =
    val box = Box(V(0, 0, 0), V(cellSize, 3 * cellSize, 0))
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.2f, 0.8f, 0.2f))
    Geometry(box, mat)
