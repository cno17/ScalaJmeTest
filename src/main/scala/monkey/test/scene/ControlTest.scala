package monkey.test.scene

import com.jme3.app.SimpleApplication
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.FastMath
import com.jme3.renderer.RenderManager
import com.jme3.renderer.ViewPort
import com.jme3.scene.Geometry
import com.jme3.scene.control.AbstractControl
import com.jme3.scene.shape.Box

// Is there a better way to set a spatials world pos?

object ControlTest extends SimpleApplication:

  override def simpleInitApp() =
    // cam.tra.set(0f, 10f, 10f)
    // cam.lookAt(Vector3f(0f, 0f, 0f), Vector3f.UNIT_Y)
    val mat = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    mat.setColor("Color", ColorRGBA.Blue)
    mat.getAdditionalRenderState.setWireframe(true)
    val geo = Geometry("Box", Box(1f, 1f, 1f))
    geo.setMaterial(mat)
    geo.addControl(MoveControl())
    rootNode.attachChild(geo)

  def main(args: Array[String]) =
    start()

  class MoveControl extends AbstractControl:

    var angle = 0f

    override def controlUpdate(tpf: Float) =
      angle += 2 * tpf
      val x = 5 * FastMath.cos(angle)
      val y = 5 * FastMath.sin(angle)
      // spatial.localTranslation.set(x, y, 0f)
      spatial.getLocalTransform.getTranslation.set(x, y, 0f) // simplify
      spatial.updateGeometricState()

    override def controlRender(rm: RenderManager, vp: ViewPort) = {}

