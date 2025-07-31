package monkey.demo.frame

import com.jme3.collision.CollisionResults
import com.jme3.math.ColorRGBA
import com.jme3.math.Matrix3f
import com.jme3.math.Ray
import com.jme3.math.Vector3f
import monkey.util.application.simple.SimpleApp
import monkey.util.shape.FrameShape

import scala.util.Random

// highlight selected frame part and allow user to rotate

object FramePickingTest extends SimpleApp:

  override def init() =
    camera3D.setTranslation(0f, 100f, 100f)
    camera3D.lookAt(V(0f, 0f, 0f), V(0f, 1f, 0f))
    // flyCam.setEnabled(false)
    val mat = Matrix3f()
    for i <- 1 to 10 do
      val r = 50
      val a = Random.nextFloat() * 6.3f
      val x = Random.nextInt(2 * r) - r
      val y = Random.nextInt(2 * r) - r
      val z = Random.nextInt(2 * r) - r
      mat.fromAngleAxis(a, Vector3f(x, y, z))
      val frame = FrameShape(assetManager, 2.0f)
      frame.setLocalTranslation(x, y, z)
      frame.setLocalRotation(mat)
      rootNode3D.attachChild(frame)

  // use input listener
  override def update(tpf: Float) =
    val org = camera3D.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f)
    val dir = camera3D.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f)
    dir.subtractLocal(org).normalizeLocal()
    val results = CollisionResults()
    rootNode3D.collideWith(Ray(org, dir), results)
    if results.size() > 0 then
      val closest = results.getClosestCollision()
      closest.getGeometry().getMaterial().setColor("Color", ColorRGBA.Red)
      val frame = closest.getGeometry().getParent().asInstanceOf[FrameShape]
      frame.move(1f, 0f, 0f)
