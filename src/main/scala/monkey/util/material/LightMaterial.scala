package monkey.util.material

import com.jme3.asset.AssetManager
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.texture.Texture
import com.jme3.texture.Texture2D

// todo: documentation

class LightMaterial(manager: AssetManager) extends SimpleMaterial(manager):

  var delegate = Material(manager, "Common/MatDefs/Light/Lighting.j3md")
  
  init()
  
  def useMaterialColors(b: Boolean) = delegate.setBoolean("UseMaterialColors", b)
  def useVertexColors(b: Boolean) = delegate.setBoolean("UseVertexColors", b)
  def setVertexLighting(b: Boolean) = delegate.setBoolean("VertexLighting", b)
  
  def setShininess(s: Float) = delegate.setFloat("Shininess", s)
  
  def setAmbientColor(c: ColorRGBA) = delegate.setColor("Ambient", c)
  def setDiffuseColor(c: ColorRGBA) = delegate.setColor("Diffuse", c)
  def setSpecularColor(c: ColorRGBA) = delegate.setColor("Specular", c)
  
  def setDiffuseMap(t: Texture) = delegate.setTexture("DiffuseMap", t)
  def setDiffuseMap(path: String) = delegate.setTexture("DiffuseMap", path)
  def setSpecularMap(t: Texture) = delegate.setTexture("SpecularMap", t)
  def setSpecularMap(path: String) = delegate.setTexture("SpecularMap", path)
  def setNormalMap(t: Texture) = delegate.setTexture("NormalMap", t)
  def setNormalMap(path: String) = delegate.setTexture("NormalMap", path)
  
  // needed?
  def useMaterialColors = delegate.getParam("UseMaterialColors").getValue().asInstanceOf[Boolean]
  
  private def init() =
    useMaterialColors(true)
    setAmbientColor(ColorRGBA.White)
    setDiffuseColor(ColorRGBA.White)
    setSpecularColor(ColorRGBA.White)
    setShininess(45)
