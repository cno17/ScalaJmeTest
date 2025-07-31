package monkey.test.material

import com.jme3.input.KeyInput
import com.jme3.input.event.KeyInputEvent
import com.jme3.light.DirectionalLight
import com.jme3.light.Light
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Torus
import com.jme3.texture.Texture.WrapMode
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial

// use parallax map for unscaled tex coords (large features)?

object NormalMapTest extends SimpleApp:

  var geom: Geometry = null
  var light: Light = null
  var mat1: LightMaterial = null
  var mat2: LightMaterial = null
  var mat3: LightMaterial = null
  var mat4: LightMaterial = null

  override def init() =
    initBrickMaterials()
    // val mesh = Box(3, 2, 1)
    val mesh = Torus(32, 16, 1.0f, 2.0f)
    mesh.scaleTextureCoordinates(V(8.0f, 8.0f))
    geom = Geometry(mesh, mat1, true)
    // light = PointLight(V(0, 0, 10), C(1.0f, 1.0f, 1.0f), 20f)
    light = DirectionalLight(V(0, 0, -1), C(1.0f, 1.0f, 1.0f))
    rootNode3D.addChild(geom)
    rootNode3D.addLight(light)
    keyListeners += handleKeyEvent
    camera3D.setLocation(V(0, 0, 10))
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))

  def handleKeyEvent(e: KeyInputEvent) =
    val a = 0.1f
    if e.isPressed || e.isRepeating then
      if e.getKeyCode == KeyInput.KEY_UP then geom.rotate(-a, 0, 0)
      if e.getKeyCode == KeyInput.KEY_DOWN then geom.rotate(a, 0, 0)
      if e.getKeyCode == KeyInput.KEY_LEFT then geom.rotate(0, a, 0)
      if e.getKeyCode == KeyInput.KEY_RIGHT then geom.rotate(0, -a, 0)
      if e.getKeyCode == KeyInput.KEY_1 then geom.setMaterial(mat1)
      if e.getKeyCode == KeyInput.KEY_2 then geom.setMaterial(mat2)
      if e.getKeyCode == KeyInput.KEY_3 then geom.setMaterial(mat3)
      if e.getKeyCode == KeyInput.KEY_4 then geom.setMaterial(mat4)

  private def initBrickMaterials() =
    val texD = assetManager.loadTexture("Images/Bricks/Brick1Diffuse.jpg")
    val texN = assetManager.loadTexture("Images/Bricks/Brick1Normal.jpg")
    texD.setWrap(WrapMode.Repeat)
    texN.setWrap(WrapMode.Repeat)
    mat1 = LightMaterial(assetManager)
    mat1.setDiffuseMap(texD)
    mat2 = LightMaterial(assetManager)
    mat2.setDiffuseMap(texD)
    mat2.setNormalMap(texN)

  private def initMetalMaterials() =
    val texD = assetManager.loadTexture("Images/Metals/Metal2Diffuse.png")
    val texS = assetManager.loadTexture("Images/Metals/Metal2Specular.png")
    val texN = assetManager.loadTexture("Images/Metals/Metal2Normal.png")
    texD.setWrap(WrapMode.Repeat)
    texS.setWrap(WrapMode.Repeat)
    texN.setWrap(WrapMode.Repeat)
    mat1 = LightMaterial(assetManager)
    mat1.setDiffuseMap(texD)
    mat2 = LightMaterial(assetManager)
    mat2.setDiffuseMap(texD)
    mat2.setSpecularMap(texS)
    mat3 = LightMaterial(assetManager)
    mat3.setDiffuseMap(texD)
    mat3.setNormalMap(texN)
    mat4 = LightMaterial(assetManager)
    mat4.setDiffuseMap(texD)
    mat4.setSpecularMap(texS)
    mat4.setNormalMap(texN)
