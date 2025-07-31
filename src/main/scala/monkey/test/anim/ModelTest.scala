package monkey.test.anim

import com.jme3.animation.AnimControl
import com.jme3.light.AmbientLight
import monkey.util.application.simple.SimpleApp

object ModelTest extends SimpleApp:

  override def init() =
    camera3D.setTranslation(0, 0, 20)
    val model = assetManager.loadModel("Models/Sinbad/Sinbad.mesh.xml")
    val control = model.getControl(classOf[AnimControl])
    val light = AmbientLight()
    rootNode3D.addChild(model)
    rootNode3D.addLight(light)
    println(control)