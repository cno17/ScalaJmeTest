package monkey.util.extension.scene

import com.jme3.scene.Mesh

trait MeshExt:
  
  extension(m: Mesh)
    def createTangents() = 0
