package monkey.test.material

import com.jme3.material.MaterialDef
import monkey.util.application.simple.SimpleApp

object MaterialDefTest2 extends SimpleApp:

  override def init() =
    // val md = MaterialDef(am, "Materials/Geom/SimpleGeom.j3md")
    // val md = MaterialDef(assetManager, "Common/MatDefs/Blur/RadialBlur.j3md")
    val md = MaterialDef(assetManager, "Common/MatDefs/Light/Lighti.j3md")
    println(md.getName)
    println(md.getMaterialParams.size)
    println(md.getTechniqueDefsNames.size)

// for mp <- md.getParams do println("${mp.name}: ${mp.varType} = ${mp.value}")


