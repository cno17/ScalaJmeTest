package monkey.util.extension

import com.jme3.light.Light
import com.jme3.light.SpotLight
import com.jme3.math.FastMath
import com.jme3.math.FastMath

trait LightExt:

  private val d2r = FastMath.DEG_TO_RAD
  private val r2d = FastMath.RAD_TO_DEG

  extension (l: Light)
    
    def setColor() = 0
  
  extension (l: SpotLight)
    def getRange() = l.getSpotRange()
    def setRange(r: Float) = l.setSpotRange(r)
    def getInnerAngle() = l.getSpotInnerAngle()
    def getInnerAngleDeg() = l.getSpotInnerAngle() * r2d
    def setInnerAngle(a: Float) = l.setSpotInnerAngle(a)
    def setInnerAngleDeg(a: Float) = l.setSpotInnerAngle(a * d2r)
    def getOuterAngle() = l.getSpotOuterAngle()
    def getOuterAngleDeg() = l.getSpotOuterAngle() * r2d
    def setOuterAngle(a: Float) = l.setSpotOuterAngle(a)
    def setOuterAngleDeg(a: Float) = l.setSpotOuterAngle(a * d2r)
