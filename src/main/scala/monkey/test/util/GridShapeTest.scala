package monkey.test.util

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.PointLight
import monkey.util.application.simple.SimpleApp
import monkey.util.material.ColorMaterial
import monkey.util.shape.GridShape

// TODO: rotate, light, texture

object GridShapeTest extends SimpleApp:

  var grid: GridShape = null

  override def init() =

    camera3D.setTranslation(0, 20, 50)
    camera3D.lookAt(V(0f, 0f, -0f), V(0, 1, 0))
    // val tex = assetManager.loadTexture("Icons/Dada-16.png").asInstanceOf[Texture2D]
    // val tex = assetManager.loadTexture("Icons/Tree-96.png").asInstanceOf[Texture2D]
    // tex.setWrap(Texture.WrapMode.Repeat)
    val mat = ColorMaterial(assetManager)
    mat.setColor(C(0.8f, 0.8f, 0.8f))
    // mat.setColorMap(tex)
    mat.setWireframe(true)
    grid = GridShape(4f, 4f, 5, 4, mat)
    // grid."center"()
    // todo grid.translate(-grid.centerX, -grid.centerY, 0f)
    val light = PointLight()
    light.setPosition(V(0f, 10f, 0f))
    light.setColor(C(1f, 1f, 1f))
    light.setRadius(50f)
    rootNode3D.addChild(grid)
    rootNode3D.addLight(light) // extension methods are not visible ion subclasses
    keyListeners += onKeyEvent

  def onKeyEvent(e: KeyInputEvent) =
    if e.getKeyCode == KeyInput.KEY_UP then grid.rotate(-0.05f, 0, 0)
    if e.getKeyCode == KeyInput.KEY_DOWN then grid.rotate(+0.05f, 0, 0)
