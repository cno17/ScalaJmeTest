package monkey.util.extension.scene

import com.jme3.light.Light
import com.jme3.light.Light
import com.jme3.scene.Node
import com.jme3.scene.Spatial
import com.jme3.scene.control.Control
import com.jme3.scene.control.Control

trait NodeExt:

  extension (n: Node)
    
    def getChildCount = n.getQuantity()

    def addChild(s: Spatial) = n.attachChild(s)
    def addChild(s: Spatial, i: Int) = n.attachChildAt(s, i)
    
    def removeChild(s: Spatial) = n.detachChild(s)
    def removeChild(s: String) = n.detachChildNamed(s)
    def removeChild(i: Int) = n.detachChildAt(i)
    
    def clearChildren() = n.detachAllChildren()
