package monkey.util.extension.renderer

import com.jme3.math.Quaternion
import com.jme3.math.Vector3f
import com.jme3.renderer.Camera
import com.jme3.util.TempVars
import monkey.util.extension.math.MathExt
import monkey.util.math.Frustum

/**
 * the camera looks along its z-axis:
 *
 * rot = (left, up, dir)
 */

trait CameraExt extends MathExt:

  extension (c: Camera)

    def getTranslation: Vector3f =
      c.getLocation()

    def setTranslation(t: Vector3f): Camera =
      c.setLocation(t)
      c

    def setTranslation(x: Float, y: Float, z: Float): Camera =
      c.getLocation().set(x, y, z)
      c.onFrameChange()
      c

    def translate(t: Vector3f): Camera =
      c.getLocation().addLocal(t)
      c.onFrameChange()
      c

    def translate(x: Float, y: Float, z: Float): Camera =
      c.getLocation().addLocal(x, y, z)
      c.onFrameChange()
      c

    def setRotation(left: Vector3f, up: Vector3f, dir: Vector3f): Camera =
      c.setAxes(left, up, dir)
      c

    def rotate(q: Quaternion): Camera =
      c.getRotation.multLocal(q)
      c.onFrameChange()
      c

    def rotateX(a: Float): Camera =
      c.getRotation.rotateX(a)
      c.onFrameChange()
      c
      
    def rotateY(a: Float): Camera =
      c.getRotation.rotateY(a)
      c.onFrameChange()
      c
      
    def rotateZ(a: Float): Camera =
      c.getRotation.rotateZ(a)
      c.onFrameChange()
      c

    def moveTo(pos: Vector3f): Camera = c.setTranslation(pos)  
    
    def moveTo(x: Float, y: Float, z: Float): Camera = c.setTranslation(x, y, z)  
      
    // def lookAt(pos: Vector3f, up: Vector3f = V(0, 1, 0)) = c.lookAt(pos, up)

    def lookAlong(dir: Vector3f, up: Vector3f = V(0, 1, 0)) =
      c.lookAtDirection(dir, up)

    def getFrustum(res: Frustum) =
      res.left = c.getFrustumLeft()
      res.right = c.getFrustumRight()
      res.bottom = c.getFrustumBottom()
      res.top = c.getFrustumTop()
      res.near = c.getFrustumNear()
      res.far = c.getFrustumFar()

    def setFrustom(f: Frustum) =
      c.setFrustumLeft(f.left)
      c.setFrustumRight(f.right)
      c.setFrustumBottom(f.bottom)
      c.setFrustumTop(f.top)
      c.setFrustumNear(f.near)
      c.setFrustumFar(f.far)
