package monkey.test.input

import com.jme3.app.SimpleApplication
import com.jme3.input.MouseInput
import com.jme3.input.controls.ActionListener
import com.jme3.input.controls.AnalogListener
import com.jme3.input.controls.MouseAxisTrigger
import com.jme3.input.controls.MouseButtonTrigger

object MouseInputTest extends SimpleApplication, ActionListener, AnalogListener:

  val NAME_JUMP = "Jump"
  val NAME_SHOOT = "Shoot"
  val NAME_ROT_M = "RotM"
  val NAME_ROT_P = "RotP"
  val NAME_TRA_M = "TraM"
  val NAME_TRA_P = "TraP"

  override def simpleInitApp() =
    inputManager.addMapping(NAME_JUMP, MouseButtonTrigger(MouseInput.BUTTON_LEFT))
    inputManager.addMapping(NAME_SHOOT, MouseButtonTrigger(MouseInput.BUTTON_RIGHT))
    inputManager.addMapping(NAME_ROT_M, MouseAxisTrigger(MouseInput.AXIS_X, true))
    inputManager.addMapping(NAME_ROT_P, MouseAxisTrigger(MouseInput.AXIS_X, false))
    inputManager.addMapping(NAME_TRA_M, MouseAxisTrigger(MouseInput.AXIS_WHEEL, true))
    inputManager.addMapping(NAME_TRA_P, MouseAxisTrigger(MouseInput.AXIS_WHEEL, false))
    inputManager.addListener(this, NAME_JUMP, NAME_SHOOT)
    inputManager.addListener(this, NAME_ROT_M, NAME_ROT_P)
    inputManager.addListener(this, NAME_TRA_M, NAME_TRA_P)


  override def onAction(name: String, pressed: Boolean, tpf: Float) =
    if pressed then
      if NAME_JUMP == name then println(name)
      if NAME_SHOOT == name then println(name)

  override def onAnalog(name: String, value: Float, tpf: Float) =
    if NAME_ROT_M == name then println("$name ${value * 10000}")
    if NAME_ROT_P == name then println("$name ${value * 10000}")


  def main(args: Array[String]) = start()

