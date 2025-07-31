package monkey.util.extension.bullet

import com.jme3.bullet.objects.PhysicsRigidBody
import com.jme3.math.Vector3f

trait BulletExt:

    extension (b: PhysicsRigidBody)
        def getLinearPosition() = b.getPhysicsLocation()
        def getLinearPosition(res: Vector3f) = b.getPhysicsLocation(res)
        // def linPos_=(v: Vector3f) = b.setPhysicsLocation(v)
        // linVel, angPos, ...
        // def applyForce = 0
        def applyForceAt = 0
        // ...
