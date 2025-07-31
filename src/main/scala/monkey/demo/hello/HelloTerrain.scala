package monkey.demo.hello

import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.terrain.geomipmap.TerrainLodControl
import com.jme3.terrain.geomipmap.TerrainQuad
import com.jme3.terrain.geomipmap.lodcalc.DistanceLodCalculator
import com.jme3.terrain.heightmap.HillHeightMap
import monkey.util.application.simple.SimpleApp

object HelloTerrain extends SimpleApp:

  val patchSize = 65
  val totalSize = 513 // of quad

  override def init() =
    // flyCam.setMoveSpeed(50f)

    val mat = Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md")
    mat.getAdditionalRenderState().setWireframe(true)
    mat.setColor("Color", ColorRGBA.Green)

    val tex = assetManager.loadTexture("Textures/Terrain/Splat/mountains512.png")
    // val map = ImageBasedHeightMap(tex.image)
    val map = HillHeightMap(totalSize, 1000, 50f, 100f, 0) // seed!
    map.load()

    /* 3. We have prepared material and heightmap.
     * Now we create the actual terrain:
     * 3.1) Create a TerrainQuad and name it "my terrain".
     * 3.2) A good value for terrain tiles is 64x64 -- so we supply 64+1=65.
     * 3.3) We prepared a heightmap of size 512x512 -- so we supply 512+1=513.
     * 3.4) As LOD step scale we supply Vector3f(1,1,1).
     * 3.5) We supply the prepared heightmap itself.
     */

    val terrain = TerrainQuad("my terrain", patchSize, totalSize, map.getHeightMap())
    terrain.setMaterial(mat)
    terrain.setLocalTranslation(0f, -100f, 0f)
    terrain.setLocalScale(2f, 0.5f, 2f)
    rootNode3D.attachChild(terrain)

    /* 5. The LOD (level of detail) depends on were the camera is: */
    val control = TerrainLodControl(terrain, camera3D)
    control.setLodCalculator(DistanceLodCalculator(patchSize, 2.7f))
    terrain.addControl(control)
