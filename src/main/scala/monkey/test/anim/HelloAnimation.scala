package monkey.test.anim

import com.jme3.anim.AnimComposer
import com.jme3.anim.tween.Tweens
import com.jme3.anim.tween.action.Action
import com.jme3.anim.tween.action.LinearBlendSpace
import com.jme3.input.KeyInput
import com.jme3.input.controls.ActionListener
import com.jme3.input.controls.KeyTrigger
import com.jme3.light.DirectionalLight
import com.jme3.math.ColorRGBA
import com.jme3.scene.Node
import monkey.util.application.simple.SimpleApp

// todo: read tutorial!

object HelloAnimation extends SimpleApp, ActionListener:

  var advance: Action = null
  var control: AnimComposer = null

  override def init() =
    viewPort3D.setBackgroundColor(ColorRGBA.LightGray)
    initKeys()

    val dl = DirectionalLight()
    dl.setDirection(V(-0.1f, -1f, -1).normalizeLocal())
    rootNode3D.addLight(dl)

    // Load a model that contains animation
    val player = assetManager.loadModel("Models/Oto/Oto.mesh.xml").asInstanceOf[Node]
    player.setLocalScale(0.5f)
    rootNode3D.attachChild(player)

    // Use the models AnimComposer to play its "stand" animation clip
    control = player.getControl(classOf[AnimComposer])
    control.setCurrentAction("stand")

    // Compose an animation action named "halt" that transitions from 
    // "Walk" to "stand" in half a second. */
    val quickBlend = LinearBlendSpace(0f, 0.5f)
    val halt = control.actionBlended("halt", quickBlend, "stand", "Walk")
    halt.setLength(0.5)

    // Compose an animation action named "advance" that walks for one cycle, 
    // then halts, then invokes onAdvanceDone(). */
    val walk = control.action("Walk")
    val doneTween = Tweens.callMethod(this, "onAdvanceDone")
    advance = control.actionSequence("advance", walk, halt, doneTween)


  override def onAction(name: String, isPressed: Boolean, tpf: Float) =
    if isPressed && control.getCurrentAction != advance then
      control.setCurrentAction("advance")

  def initKeys() =
    inputManager.addMapping("Walk", KeyTrigger(KeyInput.KEY_SPACE))
    inputManager.addListener(this, "Walk")

  // Callback to indicate that the "advance" animation action has completed.
  def onAdvanceDone() =
    // Play the "stand" animation action.
    control.setCurrentAction("stand")


