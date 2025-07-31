package monkey.test.input

import com.jme3.app.SimpleApplication
import com.jme3.input.KeyInput
import com.jme3.input.controls.ActionListener
import com.jme3.input.controls.KeyTrigger

/**
 * TODO: do not implement interface, pass method reference to IM.addListener()
 */
object KeyInputTest extends SimpleApplication(), ActionListener:

  val NAME_ROT_XP = "RotX+"
  val NAME_ROT_XM = "RotX-"

  override def simpleInitApp() =
    inputManager.addMapping(NAME_ROT_XM, KeyTrigger(KeyInput.KEY_LEFT))
    inputManager.addMapping(NAME_ROT_XM, KeyTrigger(KeyInput.KEY_L))
    inputManager.addMapping(NAME_ROT_XP, KeyTrigger(KeyInput.KEY_RIGHT))
    inputManager.addMapping(NAME_ROT_XP, KeyTrigger(KeyInput.KEY_R))
    // inputManager.addListener(this, NAME_ROT_XP, NAME_ROT_XM)
    inputManager.addListener(this, NAME_ROT_XM)
    inputManager.addListener(this, NAME_ROT_XP)
  

  override def onAction(name: String, pressed: Boolean, tpf: Float) =
    if pressed then
      if NAME_ROT_XM == name then println(name)
      if NAME_ROT_XP == name then println(name)

  def main(args: Array[String]) =
    start()
