package monkey.demo.grid

import monkey.util.grid.Grid

object GridTest:

  def main(args: Array[String]) =
    val g = Grid(3, 3, 1, 1)
    println(g)
    g.scrollE()
    println(g)
    g.scrollN()
    println(g)
