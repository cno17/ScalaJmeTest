package monkey.test.terrain

import com.jme3.input.event.MouseMotionEvent
import com.jme3.light.PointLight
import com.jme3.material.Material
import com.jme3.math.FastMath
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.system.AppSettings
import com.jme3.terrain.heightmap.*
import monkey.util.application.old.MonkeyApp
import monkey.util.extension.MonkeyExt
import monkey.util.terrain.TerrainMesh

object HeightMapSplitTest extends MonkeyExt: 

    val size = 128

    def main(args: Array[String]) =
        val m = HillHeightMap(size, 100, 0.1f * size, 0.2f * size)
        val ms = m.split(16)
        println(m.findMinMaxHeights()(1))
        println(ms(2)(3).findMinMaxHeights()(1))
