package monkey.util.application

import com.jme3.audio.AudioRenderer
import com.jme3.audio.Listener

trait AudioComponent:

  var audioRenderer: AudioRenderer = null
  var audioListener: Listener = null

  def initAudio() = {}
  def updateAudio(tpf: Float) = {}
  def exitAudio() = {}
