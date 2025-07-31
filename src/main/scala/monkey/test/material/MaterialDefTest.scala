package monkey.test.material

import com.jme3.asset.DesktopAssetManager
import com.jme3.material.MaterialDef

object MaterialDefTest:

  def main(args: Array[String]) =

    val am = DesktopAssetManager()
    am.registerLoader("com.jme3.texture.plugins.AWTLoader", "bmp", "gif", "jpg", "png")
    am.registerLoader("com.jme3.material.plugins.J3MLoader", "j3m", "j3md")
    am.registerLocator("", "com.jme3.asset.plugins.ClasspathLocator")

    // val md = MaterialDef(am, "Materials/Geom/SimpleGeom.j3md")
    val md = MaterialDef(am, "Common/MatDefs/Blur/RadialBlur.j3md")
    println(md.getName)
    println(md.getMaterialParams.size)
    println(md.getTechniqueDefsNames.size)

// for mp <- md.getParams do println("${mp.name}: ${mp.varType} = ${mp.value}")


