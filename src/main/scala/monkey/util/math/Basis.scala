package monkey.util.math

import com.jme3.math.Vector3f

import monkey.util.extension.math.VectorExt

class Basis(val dirX: Vector3f, val dirY: Vector3f, val dirZ: Vector3f) extends VectorExt

// class Frame3(dirX: Vector3f, dirY: Vector3f, dirZ: Vector3f, val org: Vector3f) extends Basis(dirX, dirY, dirZ)
