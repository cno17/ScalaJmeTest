package monkey.test.anim

import com.jme3.anim.AnimClip
import com.jme3.anim.AnimComposer
import com.jme3.anim.TransformTrack
import com.jme3.light.AmbientLight
import com.jme3.light.DirectionalLight
import com.jme3.math.Quaternion
import com.jme3.math.Vector3f
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import monkey.util.application.simple.SimpleApp

object SpatialAnimTest extends SimpleApp:

  override def init() =
    // cam.setTranslation(0, 0, 5)

    val al = AmbientLight()
    rootNode3D.addLight(al)

    val dl = DirectionalLight()
    dl.setDirection(Vector3f.UNIT_XYZ.negate())
    rootNode3D.addLight(dl)

    // Create model
    val box = Box(1, 1, 1)
    val geom = new Geometry("box", box)
    geom.setMaterial(assetManager.loadMaterial("Textures/Terrain/BrickWall/BrickWall.j3m"))
    val model = Node("model")
    model.attachChild(geom)

    val child = Box(0.5f, 0.5f, 0.5f)
    val childGeom = new Geometry("box", child)
    childGeom.setMaterial(assetManager.loadMaterial("Textures/Terrain/BrickWall/BrickWall.j3m"))
    val childModel = Node("childmodel")
    childModel.setLocalTranslation(2, 2, 2)
    childModel.attachChild(childGeom)
    model.attachChild(childModel)

    //animation parameters
    var animTime = 5
    var fps = 25
    var totalXLength = 10

    //calculating frames
    var totalFrames = (fps * animTime).toInt
    var dT = animTime / totalFrames
    var t = 0
    var dX = totalXLength / totalFrames
    var x = 0f
    var times = new Array[Float](totalFrames)
    var translations = new Array[Vector3f](totalFrames)
    var rotations = new Array[Quaternion](totalFrames)
    var scales = new Array[Vector3f](totalFrames)
    for i <- 0 to totalFrames - 1 do
      times(i) = t
      t += dT
      translations(i) = Vector3f(x, 0, 0)
      x += dX
      rotations(i) = Quaternion.IDENTITY
      scales(i) = Vector3f.UNIT_XYZ

    val transformTrack = TransformTrack(geom, times, translations, rotations, scales)
    val transformTrackChild = TransformTrack(childGeom, times, translations, rotations, scales)
    // creating the animation
    val animClip = AnimClip("anim")
    animClip.setTracks(Array(transformTrack, transformTrackChild))

    // create spatial animation control
    val animComposer = AnimComposer()
    animComposer.addAnimClip(animClip)

    model.addControl(animComposer)
    rootNode3D.attachChild(model)

    // run animation
    model.getControl(classOf[AnimComposer]).setCurrentAction("anim")

