package monkey.util.extension.renderer

import com.jme3.post.SceneProcessor
import com.jme3.renderer.ViewPort
import com.jme3.scene.Spatial

trait ViewportExt:

  extension (v: ViewPort)

    def addScene(s: Spatial) = v.attachScene(s)
    def removeScene(s: Spatial) = v.detachScene(s)
