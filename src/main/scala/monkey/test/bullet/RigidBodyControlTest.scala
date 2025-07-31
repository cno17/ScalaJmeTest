package monkey.test.bullet

import com.jme3.bullet.BulletAppState
import com.jme3.bullet.control.RigidBodyControl
import com.jme3.scene.shape.Box
import com.jme3.scene.shape.Cylinder
import com.jme3.scene.shape.Sphere
import com.jme3.scene.shape.Torus
import monkey.util.application.simple.SimpleApp

object RigidBodyControlTest extends SimpleApp:

  override def init() =
    // val mesh = Box(1f, 1f, 1f)
    // val mesh = Sphere(8, 8, 1f)
    // val mesh = Cylinder(4, 4, 1f, 1f) // -> Hull
    val mesh = Torus(4, 4, 4f, 1f) // -> Hull
    val control = RigidBodyControl(1)
    val geo = Geometry(mesh)
    geo.addControl(control)
    println(control.getCollisionShape.getClass.getName)


