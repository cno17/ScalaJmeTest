package monkey.util.application

import com.jme3.app.Application
import com.jme3.renderer.Camera
import com.jme3.renderer.RenderManager
import com.jme3.renderer.Renderer
import com.jme3.renderer.ViewPort
import com.jme3.renderer.queue.RenderQueue.Bucket
import com.jme3.scene.Node
import com.jme3.scene.Spatial.CullHint
import com.jme3.system.AppSettings
import monkey.util.application.App
import monkey.util.extension.math.VectorExt

trait VideoComponent extends Application, VectorExt:
  
  this: App =>
  
  var renderer: Renderer = null
  var renderManager: RenderManager = null
  var camera2D: Camera = null
  var camera3D: Camera = null
  var rootNode2D: Node = null
  var rootNode3D: Node = null
  var viewPort2D: ViewPort = null
  var viewPort3D: ViewPort = null

  def initVideo() =
    val w = settings.getWidth
    val h = settings.getHeight
    renderer = context.getRenderer
    renderManager = RenderManager(renderer)
    renderManager.setTimer(timer)
    camera2D = Camera(w, h)
    camera3D = Camera(w, h)
    camera3D.setFrustumPerspective(45f, w.toFloat / h, 1f, 1000f)
    camera3D.setLocation(V(0, 0, 10))
    camera3D.lookAt(V(0, 0, 0), V(0, 1, 0))
    rootNode2D = Node("RootNode2D")
    rootNode2D.setQueueBucket(Bucket.Gui);
    rootNode2D.setCullHint(CullHint.Never);
    rootNode3D = Node("RootNode3D")
    viewPort2D = renderManager.createPostView("Gui Default", camera2D);
    // viewPort2D = renderManager.createMainView("ViewPort2D", camera2D)
    viewPort3D = renderManager.createMainView("ViewPort3D", camera3D)
    viewPort2D.setClearFlags(false, false, false);
    // viewPort2D.setClearFlags(true, true, true)
    viewPort3D.setClearFlags(true, true, true)
    viewPort2D.attachScene(rootNode2D)
    viewPort3D.attachScene(rootNode3D)


  def updateVideo(tpf: Float) =
    rootNode2D.updateLogicalState(tpf)
    rootNode3D.updateLogicalState(tpf)
    rootNode2D.updateGeometricState()
    rootNode3D.updateGeometricState()
    renderManager.render(tpf, context.isRenderable)

  def exitVideo() = {}
  
  override def getRenderer = renderer
  override def getRenderManager = renderManager
  override def getCamera = camera3D
  override def getGuiViewPort = viewPort2D
  override def getViewPort = viewPort3D
