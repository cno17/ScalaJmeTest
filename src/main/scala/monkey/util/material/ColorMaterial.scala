package monkey.util.material

import com.jme3.asset.AssetManager
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.texture.Texture
import com.jme3.texture.Texture2D

class ColorMaterial(manager: AssetManager) extends SimpleMaterial(manager):

  var delegate = Material(manager, "Common/MatDefs/Misc/Unshaded.j3md")
  
  init()
  
  def setColor(c: ColorRGBA) = delegate.setColor("Color", c)
  def setColorMap(t: Texture) = delegate.setTexture("ColorMap", t)
  def setColorMap(s: String) = delegate.setTexture("ColorMap", manager.loadTexture(s).asInstanceOf[Texture2D])

  // needed?
  def useVertexColor(v: Boolean) = delegate.setBoolean("UseVertexColor", v)
  def useInstancing(v: Boolean) = delegate.setBoolean("UseInstancing", v)
  def setGlowColor(v: ColorRGBA) = delegate.setColor("GlowColor", v)
  def setGlowColorMap(v: Texture2D) = delegate.setTexture("GlowColorMap", v)
  def setLightMap(v: Texture2D) = delegate.setTexture("LightMap", v)
  def getUseVertexColor() = delegate.getParam("UseVertexColor").getValue().asInstanceOf[Boolean]

  private def init() =
    setColor(ColorRGBA.White)