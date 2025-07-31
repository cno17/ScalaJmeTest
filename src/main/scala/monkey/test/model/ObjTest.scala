package monkey.test.model

import com.jme3.material.Material
import com.jme3.scene.Geometry
import monkey.util.application.simple.SimpleApp

object ObjTest extends SimpleApp:

  override def init() =
    camera3D.setTranslation(0, 0, 5)
    val mat = Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
    val geo = assetManager.loadModel("Models/Teapot/Teapot.obj").asInstanceOf[Geometry]
    geo.setMaterial(mat);
    rootNode3D.attachChild(geo)
