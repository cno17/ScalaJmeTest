package monkey.util.math.integration

trait Integral:
  
  def apply(f: Float => Float, a: Float, b: Float): Float
