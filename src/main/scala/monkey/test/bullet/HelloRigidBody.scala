package monkey.test.bullet

import com.jme3.bullet.BulletAppState
import com.jme3.bullet.collision.shapes.SphereCollisionShape
import com.jme3.bullet.objects.PhysicsRigidBody
import monkey.util.application.simple.SimpleApp

object HelloRigidBody extends SimpleApp:

  override def init() =
    // Set up Bullet physics and create a physics space.
    val bulletAppState = BulletAppState()
    // Enable debug visualization to reveal what occurs in physics space.
    // bulletAppState.isDebugEnabled(true)
    bulletAppState.setDebugEnabled(true)
    // For clarity, simulate at 1/10th normal speed.
    bulletAppState.setSpeed(1f)
    stateManager.attach(bulletAppState)
    val physicsSpace = bulletAppState.getPhysicsSpace()


    // Create a CollisionShape for balls.
    val ballRadius = 1f
    val ballShape = SphereCollisionShape(ballRadius)
    // Create 2 balls (dynamic rigid bodies) and add them to the space.
    val ballMass = 2f
    val ball1 = PhysicsRigidBody(ballShape, ballMass)
    val ball2 = PhysicsRigidBody(ballShape, ballMass)
    physicsSpace.addCollisionObject(ball1)
    physicsSpace.addCollisionObject(ball2)

    // Locate the balls initially 2 PSU (physics-space units) apart.
    // In other words, 4 PSU from center to center.
    ball1.setPhysicsLocation(V(1, 1, 0))
    ball2.setPhysicsLocation(V(5, 1, 0))

    // Set ball #2 on a collision course with ball #1.
    // ball2.applyCentralImpulse(v(-25, 0f, 0f))

    // Minie's BulletAppState simulates the dynamics...
    println(camera3D.getLocation)
