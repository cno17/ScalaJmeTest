package monkey.test.font

import com.jme3.font.BitmapText
import com.jme3.font.Rectangle
import monkey.util.application.simple.SimpleApp

object BitmapTextTest extends SimpleApp:

  // in MonkeyApp it worked!
  // todo: looks too blurry

  override def init() =
    // val font = assetManager.loadFont("Fonts/Roboto-Regular.ttf")
    // val font = assetManager.loadFont("Fonts/FreeSerif32.fnt")
    val font = assetManager.loadFont("Interface/Fonts/Default.fnt")
    val text = BitmapText(font)
    text.setBox(Rectangle(0, 0, settings.getWidth, settings.getHeight))
    text.setSize(font.getPreferredSize() * 2) // scaling increases blur!
    text.setColor(C(0.2f, 0.8f, 0.2f))
    text.setText("Hello")
    text.setLocalTranslation(0, text.getHeight(), 0)
    rootNode2D.attachChild(text)
