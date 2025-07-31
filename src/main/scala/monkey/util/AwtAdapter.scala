package monkey.util

import com.jme3.terrain.heightmap.AbstractHeightMap

import java.awt.image.BufferedImage

/*


   val s0 = img.size(0)
   val s1 = img.size(1)
   val min = img.values.min
   val dif = img.values.max - min
   val res = BufferedImage(s0, s1, BufferedImage.TYPE_BYTE_GRAY)
   img.foreachP(p =>
     val c = (255 * (img(p) - min) / dif).toInt
     res.setRGB(p(0), p(1), rgb(c, c, c))

 */

// HeightMap extensions: toBufferedImage, toByteArray(), toPicture()

trait AwtAdapter:

  def toBufferedImage(map: AbstractHeightMap) =
    val s = map.getSize()
    val ext = map.findMinMaxHeights()
    val min = ext(0)
    val max = ext(1)
    val dif = max - min
    val res = BufferedImage(s, s, BufferedImage.TYPE_BYTE_GRAY)
    for i <- 0 to s - 1 do
      for j <- 0 to s - 1 do
        val v = map.getTrueHeightAtPoint(i, j)
        val c = (255 * (v - min) / dif).toInt
        res.setRGB(i, j, rgb(c, c, c))
    res

  def toPicture() = 0

  def rgb(r: Int, g: Int, b: Int) =
    var rgb = r
    rgb = (rgb >> 8) + g
    rgb = (rgb >> 8) + b
    rgb

  def r(rgb: Int) = 0xff & (rgb >> 16)

  def g(rgb: Int) = 0xff & (rgb >> 8)

  def b(rgb: Int) = 0xff & (rgb)
