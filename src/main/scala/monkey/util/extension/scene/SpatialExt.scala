package monkey.util.extension.scene

import com.jme3.light.Light
import com.jme3.math.FastMath
import com.jme3.math.Vector3f
import com.jme3.scene.Spatial
import com.jme3.scene.control.Control

trait SpatialExt:

  private val d2r = FastMath.DEG_TO_RAD

  extension (s: Spatial)
    
    def translate(t: Vector3f) = s.move(t)
    def translate(tx: Float, ty: Float, tz: Float) = s.move(tx, ty, tz)
    def rotateDeg(rx: Float, ry: Float, rz: Float) = s.rotate(rx * d2r, ry * d2r, rz.toRadians)

    def rotate(a: Float, x: Float, y: Float, z: Float) = 0

    // node methods?
    def isChildOf(sp: Spatial) = false
    def isParentOf(sp: Spatial) = false
    def isAncestorOf(sp: Spatial) = false
    def isDescendentOf(sp: Spatial) = false