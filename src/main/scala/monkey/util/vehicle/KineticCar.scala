package monkey.util.vehicle

import com.jme3.asset.AssetManager
import com.jme3.math.Vector3f
import com.jme3.scene.Node
import com.jme3.scene.Spatial
import com.jme3.scene.shape.Box
import monkey.util.extension.MonkeyExt
import monkey.util.material.ColorMaterial

// the car aligns its x-axis with vel

class KineticCar(am: AssetManager) extends Node, MonkeyExt:

  var radX = 3.0f
  var radY = 2.0f
  var radZ = 1.0f
  
  var maxVel = 1.0f
  var maxAcc = 0.5f
  
  val vel = Vector3f()
  val acc = Vector3f()
  
  def pos = getWorldTranslation

  def initialize() =
    val mat = ColorMaterial(am)
    mat.setColor(C(0.8f, 0.8f, 0.2f))
    attachChild(Geometry(Box(radX, radY, radZ), mat))
    setLocalTranslation(0, 0, radZ)
    
  
  override def clone() = null