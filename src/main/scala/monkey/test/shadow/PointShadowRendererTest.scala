package monkey.test.shadow

import com.jme3.light.PointLight
import com.jme3.renderer.queue.RenderQueue
import com.jme3.scene.Geometry
import com.jme3.scene.shape.Box
import com.jme3.shadow.PointLightShadowRenderer
import monkey.util.application.simple.SimpleApp
import monkey.util.material.LightMaterial

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object PointShadowRendererTest extends SimpleApp:

  val FLOOR_RADIUS = 50f

  var floor: Geometry = null
  var boxes = ArrayBuffer[Geometry]()
  var lightL: PointLight = null
  var lightS: PointLight = null

  override def init() =

    camera3D.setTranslation(0, 75, 100)
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    floor = floor1()
    for i <- 0 to 50 do boxes += box1()

    lightL = PointLight(V(0f, 50f, 20f), C(1.0f, 1.0f, 1.0f))
    lightS = PointLight(V(0f, 50f, 20f), C(1.0f, 1.0f, 1.0f))
    lightS.setRadius(500)

    val shadowP = PointLightShadowRenderer(assetManager, 512)
    shadowP.setLight(lightS)
    viewPort3D.addProcessor(shadowP)

    rootNode3D.addLight(lightL)
    for box <- boxes do rootNode3D.attachChild(box)

    rootNode3D.addChild(floor)
    rootNode3D.setShadowMode(RenderQueue.ShadowMode.CastAndReceive)

  override def update(tpf: Float) =
    for box <- boxes do
      val rx = 0.01f * Random.nextFloat()
      val ry = 0.02f * Random.nextFloat()
      val rz = 0.03f * Random.nextFloat()
      box.rotate(rx, ry, rz)

  private def floor1(): Geometry =
    val mat = LightMaterial(assetManager)
    mat.useMaterialColors(true)
    mat.setDiffuseColor(C(0.5f, 0.5f, 0.5f))
    mat.setSpecularColor(C(0.8f, 0.8f, 0.8f))
    mat.setShininess(50)
    floor = new Geometry("", Box(FLOOR_RADIUS, 1f, FLOOR_RADIUS))
    floor.setMaterial(mat.delegate)
    floor

  private def box1(): Geometry =
    val cr = Random.nextFloat()
    val cg = Random.nextFloat()
    val cb = Random.nextFloat()
    val mat = LightMaterial(assetManager)
    mat.useMaterialColors(true)
    mat.setDiffuseColor(C(cr, cg, cb))
    mat.setSpecularColor(C(0.8f, 0.8f, 0.8f))
    mat.setShininess(127 * Random.nextFloat())
    val fr = FLOOR_RADIUS
    val rx = fr * 0.05f * Random.nextFloat()
    val ry = fr * 0.08f * Random.nextFloat()
    val rz = fr * 0.10f * Random.nextFloat()
    val tx = fr * (-0.5f + 1.0f * Random.nextFloat())
    val ty = fr * (-0.0f + 1.0f * Random.nextFloat())
    val tz = fr * (-0.5f + 1.0f * Random.nextFloat())
    val geo = new Geometry("", Box(rx, ry, rz))
    geo.translate(tx, ty, tz)
    geo.setMaterial(mat.delegate)
    geo
