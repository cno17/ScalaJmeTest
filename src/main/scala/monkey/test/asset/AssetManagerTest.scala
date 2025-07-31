package monkey.test.asset

import com.jme3.asset.plugins.ClasspathLocator
import com.jme3.asset.plugins.FileLocator
import com.jme3.audio.plugins.OGGLoader
import com.jme3.audio.plugins.WAVLoader
import com.jme3.material.plugins.J3MLoader
import com.jme3.system.JmeSystem
import com.jme3.texture.plugins.AWTLoader
import monkey.util.material.ColorMaterial

import java.io.File

object AssetManagerTest:

  def main(args: Array[String]) =
    val mgr = JmeSystem.newAssetManager()
    mgr.registerLocator("src/main/resources", classOf[FileLocator])
    mgr.registerLocator("", classOf[ClasspathLocator])
    mgr.registerLoader(classOf[AWTLoader], "jpg")
    mgr.registerLoader(classOf[WAVLoader], "wav")
    mgr.registerLoader(classOf[OGGLoader], "ogg")
    mgr.registerLoader(classOf[J3MLoader], "j3m", "j3md")
    val tex = mgr.loadTexture("Images/Stone1.jpg")
    val ad1 = mgr.loadAudio("Sounds/Gun.wav")
    val ad2 = mgr.loadAudio("Sounds/Steps.ogg")
    println(tex.getImage.getWidth)
    println(ad1.getDataType)
    println(ad2.getDataType)
    val mat = ColorMaterial(mgr)
    println(mat)