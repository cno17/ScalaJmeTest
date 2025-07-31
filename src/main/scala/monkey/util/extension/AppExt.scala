package monkey.util.extension

import com.jme3.app.SimpleApplication
import com.jme3.app.state.AppState

trait AppExt:

    extension (a: SimpleApplication)
        def removeAppStates() =
            val sm = a.getStateManager()
            val c1 = Class.forName("com.jme3.app.DebugKeysAppState").asInstanceOf[Class[AppState]]
            val c2 = Class.forName("com.jme3.app.FlyCamAppState").asInstanceOf[Class[AppState]]
            val c3 = Class.forName("com.jme3.app.StatsAppState").asInstanceOf[Class[AppState]]
            sm.detach(sm.getState(c1))
            sm.detach(sm.getState(c2))
            sm.detach(sm.getState(c3))



/*
def assetManager = a.getAssetManager()
def camera = a.getCamera()
def context = a.getContext()
def guiNode = a.getGuiNode()
def guiViewPort = a.getGuiViewPort()
def inputManager = a.getInputManager()
def renderer = a.getRenderer()
def renderManager = a.getRenderManager()
def rootNode = a.getRootNode()
def stateManager = a.getStateManager()
def timer = a.getTimer()
*/


            