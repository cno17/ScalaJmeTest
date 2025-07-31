package monkey.util.mesh.par

import com.jme3.bounding.BoundingBox
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import com.jme3.scene.VertexBuffer
import monkey.util.extension.math.VectorExt
import org.lwjgl.util.par.ParShapes.par_shapes__compute_welded_normals
import org.lwjgl.util.par.ParShapes.par_shapes_compute_aabb
import org.lwjgl.util.par.ParShapes.par_shapes_compute_normals
import org.lwjgl.util.par.ParShapes.par_shapes_create_cone
import org.lwjgl.util.par.ParShapes.par_shapes_create_cylinder
import org.lwjgl.util.par.ParShapes.par_shapes_create_disk
import org.lwjgl.util.par.ParShapes.par_shapes_create_dodecahedron
import org.lwjgl.util.par.ParShapes.par_shapes_create_icosahedron
import org.lwjgl.util.par.ParShapes.par_shapes_create_klein_bottle
import org.lwjgl.util.par.ParShapes.par_shapes_create_plane
import org.lwjgl.util.par.ParShapes.par_shapes_create_torus
import org.lwjgl.util.par.ParShapes.par_shapes_create_trefoil_knot
import org.lwjgl.util.par.ParShapes.par_shapes_free_mesh
import org.lwjgl.util.par.ParShapes.par_shapes_merge
import org.lwjgl.util.par.ParShapesMesh

// todo: more create methods!

object TriMesh:

  def cone(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_cone(slices, stacks))

  def cylinder(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_cylinder(slices, stacks))

  def disc(slices: Int, radius: Float, center: Vector3f, normal: Vector3f) =
    val c = Array(center.x, center.y, center.z)
    val n = Array(normal.x, normal.y, normal.z)
    TriMesh(par_shapes_create_disk(radius, slices, c, n))

  // no tec
  def dodecahedron() =
    TriMesh(par_shapes_create_dodecahedron())

  // no tec
  def icosahedron() =
    TriMesh(par_shapes_create_icosahedron())

  def kleinBottle(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_klein_bottle(slices, stacks))

  def plane(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_plane(slices, stacks))

  // todo outerRadius: scale
  def torus(slices: Int, stacks: Int, innerRadius: Float) =
    TriMesh(par_shapes_create_torus(slices, stacks, innerRadius))

  def trefoilKnot(slices: Int, stacks: Int, radius: Float) =
    TriMesh(par_shapes_create_trefoil_knot(slices, stacks, radius))

// All meshes have positions and indices.
// Some also have normals and texcoords.
// ToDo: use assimp to create tangents from texcoords!

// todo: compute normals, if not present

class TriMesh(val delegate: ParShapesMesh) extends TriMeshTransformer, VectorExt:

  // if delegate.normals(3 * delegate.npoints) == null then par_shapes_compute_normals(delegate)
  if delegate.normals(3 * delegate.npoints) == null then par_shapes__compute_welded_normals(delegate)

  val numV = delegate.npoints
  val numT = delegate.ntriangles

  val posB = delegate.points(3 * numV)
  var norB = delegate.normals(3 * numV)
  var tecB = delegate.tcoords(2 * numV)
  val indB = delegate.triangles(3 * numT)

  def computeWeldedNormals() = 
    par_shapes__compute_welded_normals(delegate)
    this
    
  def computeNormals() = 
    par_shapes_compute_normals(delegate)
    norB = delegate.normals(3 * numV)
    this
    
  def toObj() = 0

  def merge(m: TriMesh) =
    par_shapes_merge(delegate, m.delegate)
    this

  def getBoundingBox() =
    val a = new Array[Float](6)
    par_shapes_compute_aabb(delegate, a)
    BoundingBox(V(a(0), a(1), a(2)), V(a(3), a(4), a(5)))

  def destroy() =
    par_shapes_free_mesh(delegate)

  def normalize() =
    val b = getBoundingBox()
    val c = b.getCenter
    val r = Math.max(Math.max(b.getXExtent, b.getYExtent), b.getZExtent)
    translate(c.negate())
    scale(1.0f / r)

  def toMesh() =
    val mesh = Mesh()
    mesh.setMode(Mesh.Mode.Triangles)
    mesh.setBuffer(VertexBuffer.Type.Position, 3, posB)
    mesh.setBuffer(VertexBuffer.Type.Normal, 3, norB)
    mesh.setBuffer(VertexBuffer.Type.Index, 1, indB)
    if tecB != null then mesh.setBuffer(VertexBuffer.Type.TexCoord, 2, tecB)
    mesh

