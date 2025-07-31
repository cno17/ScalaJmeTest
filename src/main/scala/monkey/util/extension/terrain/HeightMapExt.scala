package monkey.util.extension.terrain

import com.jme3.asset.AssetManager
import com.jme3.asset.AssetManager
import com.jme3.terrain.heightmap.AbstractHeightMap
import com.jme3.texture.Texture2D
import com.jme3.texture.Texture2D
import com.jme3.texture.plugins.AWTLoader
import com.jme3.ui.Picture
import com.jme3.ui.Picture

import java.awt.image.BufferedImage
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

trait HeightMapExt:

  extension (h: AbstractHeightMap)
    def foreach(f: (i: Int, j: Int) => Unit) =
      for i <- 0 to h.getSize - 1 do
        for j <- 0 to h.getSize - 1 do
          f(i, j)


    // min = 0, max = maxVal
    def normalize(maxVal: Float = 1f): Unit =
      val ext = h.findMinMaxHeights()
      val min = ext(0)
      val max = ext(1)
      val dif = max - min
      if dif == 0f then return
      for i <- 0 to h.getSize - 1 do
        for j <- 0 to h.getSize - 1 do
          val v1 = h.getTrueHeightAtPoint(i, j)
          val v2 = maxVal * (v1 - min) / dif
          h.setHeightAtPoint(v2, i, j)

    def toBufferedImage(): BufferedImage =
      val s = h.getSize
      val ext = h.findMinMaxHeights()
      val min = ext(0)
      val max = ext(1)
      val dif = max - min
      val res = BufferedImage(s, s, BufferedImage.TYPE_BYTE_GRAY)
      for i <- 0 to s - 1 do
        for j <- 0 to s - 1 do
          val v = h.getTrueHeightAtPoint(i, j)
          val c = (255 * (v - min) / dif).toInt
          res.setRGB(i, j, rgb(c, c, c))

      res

    def toTexture(am: AssetManager): Texture2D =
      val img = AWTLoader().load(toBufferedImage(), false)
      val tex = Texture2D(img)
      tex


    // TODO drop file
    def toPicture(am: AssetManager): Picture =
      val img = toBufferedImage()
      System.getProperty("java.io.tmpdir")
      ImageIO.write(img, "jpg", File("Image.jpg"))
      val res = Picture("")
      res.setWidth(img.getWidth)
      res.setHeight(img.getHeight)
      res.setImage(am, "Image.jpg", false)
      res


    // move to some util or color class

    def rgb(r: Int, g: Int, b: Int): Int =
      var rgb = r
      rgb = (rgb << 8) + g
      rgb = (rgb << 8) + b
      rgb

    // kotlin shr = >> ?

    def r(rgb: Int) = 0xff & (rgb >> 16) // FF?

    def g(rgb: Int) = 0xff & (rgb >> 8)

    def b(rgb: Int) = 0xff & (rgb)
