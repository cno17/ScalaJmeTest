package monkey.util.material

import com.jme3.asset.AssetManager
import com.jme3.material.Material

// todo: context param: manager

abstract class SimpleMaterial(val manager: AssetManager):

  var delegate: Material
  
  def setLineWidth(w: Float) = delegate.getAdditionalRenderState.setLineWidth(w)
  def setWireframe(v: Boolean) = delegate.getAdditionalRenderState.setWireframe(v)
  
  extension (m: Material)
    
    def setTexture(name: String, path: String) = m.setTexture(name, manager.loadTexture(path))
