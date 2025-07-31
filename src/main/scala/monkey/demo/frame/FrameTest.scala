package monkey.demo.frame

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.math.Vector3f
import monkey.util.application.simple.SimpleApp
import monkey.util.shape.FrameShape

// Is there a better way to set a spatials world pos?

object FrameTest extends SimpleApp:

  var frame: FrameShape = null
  var f = 1f

  override def init() =
    camera3D.setTranslation(0f, 5f, 5f)
    camera3D.lookAt(Vector3f(0f, 0f, 0f), Vector3f.UNIT_Y)
    frame = FrameShape(assetManager, 0.1f)
    rootNode3D.attachChild(frame)
    keyListeners += onKeyEvent

  def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      if e.getKeyCode() == KeyInput.KEY_F then f *= -1
      if e.getKeyCode() == KeyInput.KEY_J then frame.rotate(0.1f * f, 0f, 0f)
      if e.getKeyCode() == KeyInput.KEY_K then frame.rotate(0f, 0.1f * f, 0f)
      if e.getKeyCode() == KeyInput.KEY_L then frame.rotate(0f, 0f, 0.1f * f)
