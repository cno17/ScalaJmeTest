package monkey.util.grid

import com.jme3.scene.Node

import scala.util.Random

/** i: column index j: row index
  */

// tileCountX, tileExtentX  
class Grid(
    val numI: Int, 
    val numJ: Int,
    val tileExtX: Int,
    val tileExtY: Int
) extends Node:

  val tiles = Array.fill(numI, numJ)(Tile())
  val rnd = Random()
  
  init()

  def init() =
    for i <- 0 to numI - 1 do
      for j <- 0 to numJ - 1 do
        val t = tiles(i)(j)
        t.posX = i * tileExtX
        t.posY = j * tileExtY
        t.wn = tiles(modI(i - 1))(j)
        t.en = tiles(modI(i + 1))(j)
        t.sn = tiles(i)(modJ(j - 1))
        t.nn = tiles(i)(modJ(j + 1))
        attachChild(t)

  def apply(i: Int, j: Int) = tiles(modI(i))(modJ(j))

  def posX = this(indI, indJ).posX
  def posY = this(indI, indJ).posY

  def extentX = numI * tileExtX
  def extentY = numJ * tileExtY

  def centerX = centerTile.centerX
  def centerY = centerTile.centerY

  def contains(x: Float, y: Float) =
    posX <= x && x < posX + tileExtX && posY <= y && y < posY + tileExtY

  def centerTile =
    val i = indI + numI / 2
    val j = indJ + numJ / 2
    this(i, j)

  def randomTile =
    val i = indI + rnd.nextInt() % numI
    val j = indJ + rnd.nextInt() % numJ
    this(i, j)

  def tileAt(x: Float, y: Float) = 0 // TODO

  // scrollEast
  def scrollE() =
    forEachTileInCol(indI, _.translate(extentX, 0))
    indI = modI(indI + 1)

  def scrollN() =
    forEachTileInRow(indJ, _.translate(0, extentY))
    indJ = modJ(indJ + 1)

  def scrollW() =
    forEachTileInCol(indI + numI - 1, _.translate(-extentX, 0))
    indI = modI(indI - 1)

  def scrollS() =
    forEachTileInRow(indJ + numJ - 1, _.translate(0, -extentY))
    indJ = modJ(indJ - 1)

  def forEachTile(f: Tile => Unit) =
    for j <- 0 to numJ - 1 do for i <- 0 to numI - 1 do f(this(i, j))

  def forEachTileInCol(i: Int, f: Tile => Unit) =
    for j <- 0 to numJ - 1 do f(this(i, j))

  def forEachTileInRow(j: Int, f: Tile => Unit) =
    for i <- 0 to numJ - 1 do f(this(i, j))

  override def toString() =
    val sb = StringBuilder()
    for j <- indJ + numJ - 1 to indJ by -1 do
      for i <- indI to indI + numI - 1 do
        val t = this(i, j)
        sb.append(s"($i, $j) = (${t.posX}, ${t.posY})   ")
      sb.append("\n")
    sb.toString()

  override def clone() = this

  private var indI = 0 // west
  private var indJ = 0 // south
  
  private def modI(i: Int) = if i < 0 then i + numI else i % numI
  private def modJ(i: Int) = if i < 0 then i + numJ else i % numJ


// def modI2(i: Int) = if (i > 0) i % numI else numI - (-i % numI)
