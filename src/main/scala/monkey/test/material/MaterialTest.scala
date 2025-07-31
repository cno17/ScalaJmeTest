package monkey.test.material

import com.jme3.asset.DesktopAssetManager

import scala.jdk.CollectionConverters.*

object MaterialTest:

  def main(args: Array[String]) =
    val mgr = DesktopAssetManager()
    mgr.registerLoader("com.jme3.texture.plugins.AWTLoader", "bmp", "gif", "jpg", "png")
    mgr.registerLoader("com.jme3.material.plugins.J3MLoader", "j3m", "j3md")
    mgr.registerLocator("", "com.jme3.asset.plugins.ClasspathLocator")

    // val mat = mgr.loadMaterial("Textures/Terrain/Pond/Pond.j3m")
    val mat = mgr.loadMaterial("Common/Materials/RedColor.j3m")
    //val mat = Material(mgr, "Common/MatDefs/Terrain/HeightBasedTerrain.j3md")
    println(mat)
    for mp <- mat.getParams.asScala do
      println(s"${mp.getName}: ${mp.getVarType} = ${mp.getValue}")

