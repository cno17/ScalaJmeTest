package monkey.util.extension.terrain

import com.jme3.asset.AssetManager
import com.jme3.math.Vector3f
import com.jme3.terrain.heightmap.AbstractHeightMap
import com.jme3.terrain.heightmap.HeightMap
import com.jme3.terrain.heightmap.HillHeightMap
import com.jme3.texture.Texture2D
import com.jme3.texture.plugins.AWTLoader
import com.jme3.ui.Picture

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

trait TerrainExt:

  // move this

  def rgb(r: Int, g: Int, b: Int) =
    var rgb = r
    rgb = (rgb >> 8) + g
    rgb = (rgb >> 8) + b
    rgb

  def r(rgb: Int) = 0xff & (rgb >> 16)

  def g(rgb: Int) = 0xff & (rgb >> 8)

  def b(rgb: Int) = 0xff & (rgb)

  extension (m: AbstractHeightMap)

    def foreach(f: (i: Int, j: Int) => Unit) =
      val s = m.getSize()
      for i <- 0 to s - 1 do for j <- 0 to s - 1 do f(i, j)

    def normalize(maxVal: Float = 1) =
      val ext = m.findMinMaxHeights()
      val min = ext(0)
      val max = ext(1)
      val dif = max - min
      if dif > 0 then
        // foreach?
        val s = m.getSize()
        for i <- 0 to s - 1 do
          for j <- 0 to s - 1 do
            val v1 = m.getTrueHeightAtPoint(i, j)
            val v2 = maxVal * (v1 - min) / dif
            m.setHeightAtPoint(v2, i, j)
      m

    def normalAt(i: Int, j: Int) =
      val s = m.getSize()
      val u = Vector3f()
      val v = Vector3f()
      val n = Vector3f()
      if i < s - 1 then
        val z0 = m.getTrueHeightAtPoint(i + 0, j)
        val z1 = m.getTrueHeightAtPoint(i + 1, j)
        u.set(1, 0, z1 - z0)
      else
        val z0 = m.getTrueHeightAtPoint(i - 1, j)
        val z1 = m.getTrueHeightAtPoint(i - 0, j)
        u.set(1, 0, z1 - z0)
      if (j < s - 1)
        val z0 = m.getTrueHeightAtPoint(i, j + 0)
        val z1 = m.getTrueHeightAtPoint(i, j + 1)
        v.set(0, 1, z1 - z0)
      else
        val z0 = m.getTrueHeightAtPoint(i, j - 1)
        val z1 = m.getTrueHeightAtPoint(i, j - 0)
        v.set(0, 1, z1 - z0)
      u.cross(v, n).normalizeLocal()
      // n.normalizeLocal()

    // for scrollable grids        
    def split(num: Int) =
      val size = m.getSize() / num
      val res = Array.fill(num, num)(HillHeightMap(size, 1, 0.1, 0.2f))
      for i1 <- 0 to num - 1 do
        for j1 <- 0 to num - 1 do
          val map = res(i1)(j1)
          for i2 <- 0 to size - 1 do
            for j2 <- 0 to size - 1 do
              val i = i1 * size + i2
              val j = j1 * size + j2
              val v = m.getTrueHeightAtPoint(i, j)
              map.setHeightAtPoint(v, i2, j2)
      res
      
    def toBufferedImage() =
      val s = m.getSize()
      val ext = m.findMinMaxHeights()
      val min = ext(0)
      val max = ext(1)
      val dif = max - min
      val res = BufferedImage(s, s, BufferedImage.TYPE_BYTE_GRAY)
      // foreach?
      for i <- 0 to s - 1 do
        for j <- 0 to s - 1 do
          val v = m.getTrueHeightAtPoint(i, j)
          val c = (255 * (v - min) / dif).toInt
          res.setRGB(i, j, rgb(c, c, c))

      res

    def toTexture(am: AssetManager): Texture2D =
      val img = AWTLoader().load(toBufferedImage(), false)
      Texture2D(img)

    def toPicture(am: AssetManager): Picture =
      val img = toBufferedImage()
      System.getProperty("java.io.tmpdir")
      ImageIO.write(img, "jpg", File("Image.jpg"))
      val res = Picture("")
      res.setWidth(img.getWidth().toFloat)
      res.setHeight(img.getHeight().toFloat)
      res.setImage(am, "Image.jpg", false)
      return res
