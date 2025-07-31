package monkey.util.terrain

import com.jme3.input.event.MouseMotionEvent
import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.scene.Geometry
import com.jme3.terrain.heightmap.*
import monkey.util.application.simple.SimpleApp

object TerrainViewer extends SimpleApp:

  val size = 128
  var mat: Material = null
  var geo: Geometry = null
  var light: PointLight = null

  override def init() =
    camera3D.setFrustumFar(10000f)
    camera3D.setTranslation(0, size * 0.6f, size * 1.5f)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    // mat = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    // mat.setBoolean("VertexColor", true)
    // mat.getAdditionalRenderState().setWireframe(true)
    mat = Material(assetManager, "Common/MatDefs/Light/Lighting.j3md")
    // mat.setBoolean("UseMaterialColors", true)
    mat.setColor("Diffuse", C(0.2f, 0.8f, 0.2f))
    mat.setColor("Specular", C(0.8f, 0.8f, 0.8f))
    mat.setFloat("Shininess", 3f)
    mat.setBoolean("VertexLighting", true);
    // mat.getAdditionalRenderState().setWireframe(true)

    geo = new Geometry("", TerrainMesh(createMap()))
    geo.setMaterial(mat)
    geo.rotateDeg(-90, 0, 0)

    light = PointLight()
    light.setPosition(V(0f, 20f, 20f))
    light.setPosition(V(0, size, size))
    light.setColor(C(1f, 1f, 1f))

    rootNode3D.addChild(geo)
    rootNode3D.addLight(light)
    mouseMotionListeners += onMouseMotionEvent

  def onMouseMotionEvent(e: MouseMotionEvent) =
    val rotZ = 0.008f * e.getDX()
    val rotX = 0.008f * e.getDY()
    geo.rotate(0f, 0f, rotZ)

  def createMap() =
    val seed = 0 // Random.nextInt().toLong()
    val hMap = HillHeightMap(size, 1000, 0.1f * size, 0.2f * size)
    val fMap =
      FaultHeightMap(
        size,
        1000,
        FaultHeightMap.FAULTTYPE_LINEAR,
        FaultHeightMap.FAULTSHAPE_CIRCLE,
        0.01f,
        0.02f,
        seed
      )
    val fsMap = FluidSimHeightMap(size, 200)
    // val mdMap = MidpointDisplacementHeightMap(size, 1.0f, 0.5f)
    val pdMap = ParticleDepositionHeightMap(size, 10, 100, 5000, 25000, 0.5f)
    val cMap = CombinerHeightMap(fMap, 0.5f, pdMap, 0.5f, CombinerHeightMap.ADDITION)

    val map = hMap
    map.normalize(0.2f * size)
    map
