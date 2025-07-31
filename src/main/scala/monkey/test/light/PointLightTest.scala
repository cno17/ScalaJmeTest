package monkey.test.light

import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp

object PointLightTest extends SimpleApp:

  var mat1: Material = null
  var mat2: Material = null
  var geom: Geometry = null
  var light: PointLight = null

  def createMaterials() =
    mat1 = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    mat1.setColor("Color", ColorRGBA.Blue)
    mat1.getAdditionalRenderState().setWireframe(true)
    mat2 = Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    mat2.setBoolean("UseMaterialColors", true)
    mat2.setColor("Diffuse", ColorRGBA.Red)
  // mat2.setFloat("Shininess", 3f)
  // mat2.setBoolean("VertexLighting", true);

  def createGeometry() =
    val mesh = Box(10f, 1f, 10f)
    // mesh.scaleTextureCoordinates(Vector2f(5f, 5f))
    // TangentBinormalGenerator.generate(mesh)
    geom = new Geometry("Floor", mesh)
  // floorGeom.shadowMode = RenderQueue.ShadowMode.Receive

  def createLight() =
    light = PointLight()
    light.setPosition(V(0f, 10f, 0f))
    light.setColor(C(1f, 1f, 1f))
    light.setRadius(50f)

  override def init() =

    camera3D.setTranslation(0, 20, 50)
    camera3D.lookAt(V(0f, 0f, -0f), V(0, 1, 0))
    createMaterials()
    createGeometry()
    createLight()
    geom.setMaterial(mat2)
    rootNode3D.attachChild(geom)
    rootNode3D.addLight(light)
