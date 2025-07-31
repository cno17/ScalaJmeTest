package monkey.util.frame

import com.jme3.app.SimpleApplication
import com.jme3.collision.CollisionResults
import com.jme3.math.ColorRGBA
import com.jme3.math.Matrix3f
import com.jme3.math.Ray
import com.jme3.math.Vector3f
import com.jme3.system.AppSettings
import monkey.util.input.RawInputAdapter

import scala.util.Random

// highlight selected frame part and allow user to rotate

object FramePickingTest extends SimpleApplication, RawInputAdapter:

  override def simpleInitApp() =
    // cam.location.set(0f, 100f, 100f)
    // cam.lookAt(Vector3f(0f, 0f, 0f), Vector3f(0f, 1f, 0f))
    flyCam.setEnabled(false)
    val mat = Matrix3f()
    for i <- 1 to 10 do
      val r = 50
      val a = Random.nextFloat() * 6.3f
      val x = -r + Random.nextInt(2 * r).toFloat
      val y = -r + Random.nextInt(2 * r).toFloat
      val z = -r + Random.nextInt(2 * r).toFloat
      mat.fromAngleAxis(a, Vector3f(x, y, z))
      val frame = Frame(assetManager, 2.0f)
      frame.setLocalTranslation(x, y, z)
      frame.setLocalRotation(mat)
      rootNode.attachChild(frame)

  // use input listener
  override def simpleUpdate(tpf: Float) =
    val org = cam.getWorldCoordinates(inputManager.getCursorPosition, 0.0f)
    val dir = cam.getWorldCoordinates(inputManager.getCursorPosition, 0.3f)
    dir.subtractLocal(org).normalizeLocal()
    val results = CollisionResults()
    rootNode.collideWith(Ray(org, dir), results)
    if results.size > 0 then
      val closest = results.getClosestCollision()
      closest.getGeometry.getMaterial.setColor("Color", ColorRGBA.Red)
      val frame = closest.getGeometry.getParent.asInstanceOf[Frame]
      frame.move(1f, 0f, 0f)


  def main(args: Array[String]) =
    val s = AppSettings(true)
    s.setWidth(1200)
    s.setHeight(900)
    settings = s
    start()
    
