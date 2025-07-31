package monkey.util.math

import scala.util.Random

trait Rnd: // (seed: Long = System.currentTimeMillis):

  val random = Random(System.currentTimeMillis)

  def rndI() = random.nextInt()
  def rndI(min: Int, max: Int) = min + random.nextInt(max - min)

  def rndF() = random.nextFloat()
  def rndF(min: Float, max: Float) = min + (max - min) * random.nextFloat()




