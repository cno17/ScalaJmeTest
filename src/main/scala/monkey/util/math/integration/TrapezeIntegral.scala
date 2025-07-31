package monkey.util.math.integration

import monkey.util.math.integration

import scala.math.Integral

class TrapezeIntegral extends integration.Integral:
  
  var num = 100
  
  override def apply(f: Float => Float, a: Float, b: Float) =
    val dx = (b - a) / num
    var res = (f(a) + f(b)) / 2
    for i <- 1 to num - 1 do
      res += f(i * dx)
    res
    
    
  
  
