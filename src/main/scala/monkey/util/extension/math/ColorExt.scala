package monkey.util.extension.math

import com.jme3.math.ColorRGBA

trait ColorExt:
  
  def C(r: Int, g: Int, b: Int) = ColorRGBA(r / 255f, g / 255f, b / 255f, 1f)
  def C(r: Int, g: Int, b: Int, a: Int) = ColorRGBA(r / 255f, g / 255f, b / 255f, a / 255f)
  def C(r: Float, g: Float, b: Float) = ColorRGBA(r, g, b, 1f)
  def C(r: Float, g: Float, b: Float, a: Float) = ColorRGBA(r, g, b, a)

  extension (c: ColorRGBA)
    
    def set(r: Int, g: Int, b: Int) = c.set(r / 255f, g / 255f, b / 255f, 1f)
    def set(r: Float, g: Float, b: Float) = c.set(r, g, b, 1f)
