package monkey.util.frame

import com.jme3.app.SimpleApplication
import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import monkey.test.input.RawInputAdapter

// Is there a better way to set a spatials world pos?

object FrameTest extends SimpleApplication, RawInputAdapter:

  var frame: Frame = null
  var f = 1f

  override def simpleInitApp() =
    inputManager.addRawInputListener(this)
    // cam.tra.set(0f, 5f, 5f)
    // cam.lookAt(Vector3f(0f, 0f, 0f), Vector3f.UNIT_Y)
    frame = Frame(assetManager, 0.1f)
    rootNode.attachChild(frame)


  override def onKeyEvent(e: KeyInputEvent) =
    if e.isPressed || e.isRepeating then
      if e.getKeyCode == KeyInput.KEY_F then f *= -1
      if e.getKeyCode == KeyInput.KEY_J then frame.rotate(0.1f * f, 0f, 0f)
      if e.getKeyCode == KeyInput.KEY_K then frame.rotate(0f, 0.1f * f, 0f)
      if e.getKeyCode == KeyInput.KEY_L then frame.rotate(0f, 0f, 0.1f * f)


  def main(args: Array[String]) = start()
