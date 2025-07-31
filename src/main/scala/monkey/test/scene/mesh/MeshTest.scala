package monkey.test.scene.mesh

import com.jme3.input.event.MouseMotionEvent
import com.jme3.light.PointLight
import com.jme3.scene.Geometry
import monkey.util.application.old.MonkeyApp
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial
import monkey.util.mesh.MeshBuilder
import monkey.util.mesh.par.TriMesh

object MeshTest extends SimpleApp, MeshBuilder:

  var geo: Geometry = null

  override def init() =
    camera3D.setTranslation(0, 10, 10)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))

    val mesh = TriMesh.trefoilKnot(10, 300, 0.5f).normalize().scale(3).toMesh()
    geo = Geometry(mesh, createMaterial())

    rootNode3D.attachChild(geo)
    rootNode3D.addLight(createLight())

    mouseMotionListeners += onMouseMotionEvent
  
  // override def simpleUpdate(tpf: Float) = rootNode.rotate(0.002f, 0.00f, 0.00f)

  def onMouseMotionEvent(e: MouseMotionEvent) =
    val dx = e.getDX() * 0.01f
    val dy = e.getDY() * 0.01f
    rootNode3D.rotate(dy, dx, 0)

  def createMaterial() =
    val res = LightMaterial(assetManager)
    res.useMaterialColors(true)
    res.setDiffuseColor(C(0.2f, 0.8f, 0.2f))
    res.setSpecularColor(C(0.8f, 0.8f, 0.8f))
    res.setShininess(3f)
    // res.setBoolean("VertexLighting", true);
    // res.setWireframe(true)
    res

  def createMesh() =
    val pa = Array(V(0, 0, 0), V(1, 0, 0), V(0, 1, 0))
    val na = Array(V(0, 0, 1), V(0, 0, 1), V(0, 0, 1))
    val ca = Array(V(1, 0, 0, 1), V(0, 1, 0, 1), V(0, 0, 1, 1))
    val ia = Array(0, 1, 2)
    createTriangleMesh(pa, na, ca, ia)

  def createLight() =
    val res = PointLight()
    res.setPosition(V(0f, 20f, 20f))
    res.setColor(C(1f, 1f, 1f))
    // light.setRadius(50f)
    res
