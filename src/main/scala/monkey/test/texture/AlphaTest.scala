package monkey.test.texture

import monkey.util.application.simple.SimpleApp
import monkey.util.extension.FileExt

import java.io.File

object AlphaTest extends SimpleApp, FileExt:

  override def init() =
    for f <- File("src/main/resources/Images").getChildren do
      val s = s"Images/${f.getShortName}.${f.getExtension}"
      val t = assetManager.loadTexture(s)
      println(t.getImage.getFormat)


