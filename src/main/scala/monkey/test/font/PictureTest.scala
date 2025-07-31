package monkey.test.font

import com.jme3.ui.Picture
import monkey.util.application.simple.SimpleApp

object PictureTest extends SimpleApp:

  override def init() =
    val pic = Picture("Pic")
    pic.setImage(assetManager, "Images/Bird1.jpg", false)
    println(pic.getWidth)
    println(pic.getHeight) // ???
    pic.translate(200, 200, -2)
    pic.setSize(150, 100)
    rootNode2D.addChild(pic)
