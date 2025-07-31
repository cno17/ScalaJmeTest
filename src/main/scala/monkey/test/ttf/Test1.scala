package monkey.test.ttf

import com.atr.jme.font.TrueTypeFont
import com.atr.jme.font.asset.TrueTypeKeyMesh
import com.atr.jme.font.asset.TrueTypeLoader
import com.atr.jme.font.glyph.GlyphMesh
import com.atr.jme.font.shape.TrueTypeMeshText
import com.atr.jme.font.util.StringContainer
import com.atr.jme.font.util.Style
import com.jme3.math.ColorRGBA
import monkey.util.application.simple.SimpleApp

object Test1 extends SimpleApp:

  val fontName = "Fonts/Roboto-Regular.ttf"
  val str = "Hello World"

  override def init() =
    assetManager.registerLoader(classOf[TrueTypeLoader], "ttf")
    val key = TrueTypeKeyMesh(fontName, Style.Plain, 48, 72, true)
    val font = assetManager.loadAsset(key).asInstanceOf[TrueTypeFont[GlyphMesh, TrueTypeMeshText]]
    val sc = StringContainer(font, "Hello")

    // test getFormattedText
    val ttc = font.getFormattedText(sc, ColorRGBA.White)
    rootNode2D.addChild(ttc)

    // test getText
    val text = font.getText(str, 1, ColorRGBA.White)
    text.move(0, camera2D.getHeight(), 0) // move up
    rootNode2D.addChild(text)
