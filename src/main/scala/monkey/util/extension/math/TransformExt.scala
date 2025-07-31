package monkey.util.extension.math

import com.jme3.math.*

trait TransformExt extends Matrix4Ext:

  private val Pi = FastMath.PI

  extension (t: Transform)

    // translate()
    // leftTranslate()

    def fromMatrix(m: Matrix4f) = {t.fromTransformMatrix(m); t}

    def toMatrix() = t.toTransformMatrix()
    def toMatrix(store: Matrix4f) = t.toTransformMatrix(store)

    def interpolate(t1: Transform, t2: Transform, a: Float) = {t.interpolateTransforms(t1, t2, a); t}

    // def str: String = t.toMatrix().str

/*
extension (t: Transform)
  def tra = t.getTranslation()
  // ...
  def toIdentity() = t.loadIdentity()
  def interpolate(t1: Transform, t2: Transform, a: Float) = t.interpolateTransforms(t1, t2, a)
  def fromMatrix(m: Matrix4f) = t.fromTransformMatrix(m)
  def toMatrix() = t.toTransformMatrix()
  def toMatrix(res: Matrix4f) = t.toTransformMatrix(res)
  def transform(v: Vector3f, res: Vector3f) = t.transformVector(v, res)
  def invTransform(v: Vector3f, res: Vector3f) = t.transformInverseVector(v, res)
  def inverted(res: Transform) = 0 // should be easy
*/