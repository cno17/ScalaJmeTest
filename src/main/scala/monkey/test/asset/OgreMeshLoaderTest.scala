package monkey.test.asset

import com.jme3.asset.plugins.FileLocator
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.plugins.ogre.MeshLoader
import com.jme3.scene.plugins.ogre.OgreMeshKey
import monkey.util.application.simple.SimpleApp

import java.nio.file.Path

object OgreMeshLoaderTest extends SimpleApp:

  // todo ext: AssetLoader.loadOgreMesh(path: String)
  // todo ext: AssetLoader.loadOgreSkeleton(path: String)

  // todo how to load a skeleton?

  override def init() =
    val dataDir = Path.of("").toAbsolutePath.getParent.resolve("JmeData").toFile
    val locator = FileLocator()
    locator.setRootPath(dataDir.getAbsolutePath)
    // val key = OgreMeshKey("Models/Oto/Oto.mesh.xml")
    // val key = OgreMeshKey("Models/HoverTank/Tank.mesh.xml")
    val key = OgreMeshKey("Models/Ninja/Ninja.mesh.xml")
    val info = locator.locate(assetManager, key)
    val loader = MeshLoader()
    val node = loader.load(info).asInstanceOf[Node]
    val geom = node.getChild(0).asInstanceOf[Geometry]
    val mesh = geom.getMesh
    println(mesh.getVertexCount)
    println(mesh.getTriangleCount)
