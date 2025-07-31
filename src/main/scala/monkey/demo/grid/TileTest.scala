package monkey.demo.grid

import monkey.util.grid.Tile

object TileTest:

  def main(args: Array[String]) =
    val t = Tile()
    t.posX = 5
    t.translate(2, 3)
    println(t)

    val ts = Array.fill(5, 5)(Tile())
    println(ts(0)(2))
