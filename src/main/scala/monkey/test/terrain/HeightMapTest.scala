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

object HeightMapTest extends MonkeyExt: 

    val size = 128

    def main(args: Array[String]) =
        val seed = 0 // Random.nextInt().toLong()
        val hMap = HillHeightMap(size, 1000, 0.1f * size, 0.2f * size)
        val fMap =
            FaultHeightMap(
                size,
                1000,
                FaultHeightMap.FAULTTYPE_LINEAR,
                FaultHeightMap.FAULTSHAPE_CIRCLE,
                0.01f,
                0.02f,
                seed
            )
        val fsMap = FluidSimHeightMap(size, 200)
        // val mdMap = MidpointDisplacementHeightMap(size, 1.0f, 0.5f)
        val pdMap = ParticleDepositionHeightMap(size, 10, 100, 5000, 25000, 0.5f)
        val cMap = CombinerHeightMap(fMap, 0.5f, pdMap, 0.5f, CombinerHeightMap.ADDITION)

        val map = hMap
        map.normalize(0.2f * size)
        println(2)
