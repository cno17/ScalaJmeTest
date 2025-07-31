package monkey.util.grid

import com.jme3.scene.Node

/** A tile in a grid. pos = (posX, posY): west south corner */

class Tile extends Node:

  var wn: Tile = null
  var en: Tile = null
  var sn: Tile = null
  var nn: Tile = null
  
  def grid = parent.asInstanceOf[Grid]

  // getPosX, setPosX
  
  def posX = getLocalTranslation().x
  def posX_=(v: Float) = getLocalTranslation().x = v

  def posY = getLocalTranslation().y
  def posY_=(v: Float) = getLocalTranslation().y = v

  def extentX = grid.tileExtX
  def extentY = grid.tileExtY

  def centerX = posX + extentX * 0.5f
  def centerY = posY + extentY * 0.5f

  def translate(x: Float, y: Float) = move(x, y, 0)

  def contains(x: Float, y: Float) =
    posX <= x && x < posX + extentX && posY <= y && y < posY + extentY

  override def toString() = s"Tile(posX = $posX, posY = $posY)"

  override def clone() = this
