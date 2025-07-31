package monkey.demo.book.cube_chaser

import com.jme3.renderer.RenderManager
import com.jme3.renderer.ViewPort
import com.jme3.scene.control.AbstractControl

class CubeControl extends AbstractControl:

  override def controlUpdate(tpf: Float) =
    getSpatial.rotate(0, 0, 1.0f * tpf)

  override def controlRender(rm: RenderManager, vp: ViewPort) = {}


