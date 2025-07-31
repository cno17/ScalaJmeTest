package monkey.test.scene.mesh

import monkey.util.extension.math.VectorExt
import monkey.util.mesh.par.TriMesh

object TriMeshTest extends VectorExt:


  def main(args: Array[String]) =
    val m = TriMesh.torus(10, 10, 0.3f)
    println(m.getBoundingBox())
    m.normalize()
    println(m.getBoundingBox())
    println(m.numV)
    println(m.numT)
    println(m.posB)
    println(m.norB)
    println(m.tecB)
    println(m.indB)
