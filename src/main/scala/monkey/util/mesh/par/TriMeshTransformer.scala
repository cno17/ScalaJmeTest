package monkey.util.mesh.par

import com.jme3.math.Vector3f
import org.lwjgl.util.par.ParShapes.par_shapes_rotate
import org.lwjgl.util.par.ParShapes.par_shapes_scale
import org.lwjgl.util.par.ParShapes.par_shapes_translate

trait TriMeshTransformer:
  
  this: TriMesh =>

  def scale(s: Float) =
    par_shapes_scale(delegate, s, s, s)
    this

  def scale(sx: Float, sy: Float, sz: Float) =
    par_shapes_scale(delegate, sx, sy, sz)
    this
  
  def rotate(a: Float, v: Vector3f) =
    par_shapes_rotate(delegate, a, Array(v.x, v.y, v.z))
    this
  
  def rotate(a: Float, x: Float, y: Float, z: Float) =
    par_shapes_rotate(delegate, a, Array(x, y, z))
    this
  
  def translate(t: Vector3f) =
    par_shapes_translate(delegate, t.x, t.y, t.z)
    this
  
  def translate(tx: Float, ty: Float, tz: Float) =
    par_shapes_translate(delegate, tx, ty, tz)
    this