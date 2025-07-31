package monkey.demo.book.cube_chaser

import com.jme3.asset.AssetManager
import com.jme3.scene.Geometry
import com.jme3.scene.Mesh
import com.jme3.scene.shape.Box
import monkey.util.extension.MonkeyExt
import monkey.util.material.ColorMaterial
import monkey.util.material.SimpleMaterial
import com.jme3.math.FastMath.nextRandomFloat as rndF
import monkey.util.material.LightMaterial

// rad = sceneRadius

object Cube extends MonkeyExt:

  var mesh: Mesh = null
  var mat1: LightMaterial = null
  var mat2: LightMaterial = null

  def init(m: AssetManager) =
    mesh = Box(2f, 2f, 1f)
    mat1 = LightMaterial(m)
    mat2 = LightMaterial(m)
    mat1.useMaterialColors(true)
    mat2.useMaterialColors(true)
    mat1.setDiffuseColor(C(0.8f, 0.2f, 0.2f))
    mat2.setDiffuseColor(C(0.2f, 0.8f, 0.2f))
    mat1.setSpecularColor(C(1f, 1f, 1f))
    mat2.setSpecularColor(C(1f, 1f, 1f))

class Cube(sceneExtent: Float) extends Geometry, MonkeyExt:

  var isScared = false

  init()
  
  def init() =
    val x = (rndF() - 0.5f) * sceneExtent
    val y = (rndF() - 0.5f) * sceneExtent
    setLocalTranslation(V(x, y, 0))
    setMesh(Cube.mesh)
    isScared = rndF() > 0.6f
    if isScared then
      setMaterial(Cube.mat1)
      addControl(CubeControl())
    else 
      setMaterial(Cube.mat2)

  override def clone() = null


