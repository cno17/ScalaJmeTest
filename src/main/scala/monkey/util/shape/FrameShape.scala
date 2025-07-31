package monkey.util.shape

import com.jme3.asset.AssetManager
import com.jme3.material.Material
import com.jme3.math.ColorRGBA
import com.jme3.math.FastMath
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Mesh
import com.jme3.scene.Node
import com.jme3.scene.shape.Cylinder
import com.jme3.scene.shape.Sphere

class FrameShape(val am: AssetManager, sr: Float = 0.5f) extends Shape:

  var sphereRadius = sr
  var axisRadius = sr / 5
  var axisLength = sr * 5
  var tipRadius = sr
  var tipLength = sr

  init()

  // share cylinder and "cone"?
  def init() =
    val ar = axisRadius
    val al = axisLength
    // TODO
    // val tr = tipRadius
    // val tl = tipLength
    val rad = 0.0000001f // endRadius
    val ang = FastMath.PI / 2
    val matO = mat(0.8f, 0.8f, 0.8f)
    val matX = mat(0.8f, 0.2f, 0.2f)
    val matY = mat(0.2f, 0.8f, 0.2f)
    val matZ = mat(0.2f, 0.2f, 0.8f)
    val orig = geo("orig", Sphere(8, 8, sr), matO)
    val dirX = geo("dirX", Cylinder(4, 8, ar, al), matX)
    dirX.rotate(0f, -ang, 0f)
    dirX.move(Vector3f(al / 2, 0f, 0f))
    val dirY = geo("dirY", Cylinder(4, 8, ar, al), matY)
    dirY.rotate(-ang, 0f, 0f)
    dirY.move(Vector3f(0f, al / 2, 0f))
    val dirZ = geo("dirZ", Cylinder(4, 8, ar, al), matZ)
    dirZ.move(Vector3f(0f, 0f, al / 2))
    val tipX = geo("tipX", Cylinder(4, 8, sr, rad, sr * 2, true, false), matX)
    tipX.rotate(0f, -ang, 0f)
    tipX.move(Vector3f(al + sr, 0f, 0f))
    val tipY = geo("tipY", Cylinder(4, 8, rad, sr, sr * 2, true, false), matY)
    tipY.rotate(-ang, 0f, 0f)
    tipY.move(Vector3f(0f, al + sr, 0f))
    val tipZ = geo("tipZ", Cylinder(4, 8, rad, sr, sr * 2, true, false), matZ)
    tipZ.move(Vector3f(0f, 0f, al + sr))
    addChild(orig)
    addChild(dirX)
    addChild(dirY)
    addChild(dirZ)
    addChild(tipX)
    addChild(tipY)
    addChild(tipZ)

  override def clone() = this

  private def mat(r: Float, g: Float, b: Float): Material =
    val res = Material(am, "Common/MatDefs/Misc/Unshaded.j3md")
    res.setColor("Color", ColorRGBA(r, g, b, 1f))
    res.getAdditionalRenderState().setWireframe(true)
    res


  private def geo(name: String, mesh: Mesh, mat: Material): Geometry =
    val res = new Geometry(name, mesh)
    res.setMaterial(mat)
    res
