package monkey.test.util

import java.lang.System
import scala.util.Random

/**
 * TODO: randomPoints in box or sphere
 */

trait Rnd(seed: Long = System.currentTimeMillis()):

  val rnd = Random(seed)

  def rndF(min: Float, max: Float) = min + (max - min) * rnd.nextFloat()


