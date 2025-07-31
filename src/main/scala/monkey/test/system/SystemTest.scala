package monkey.test.system

import com.jme3.system.AppSettings
import com.jme3.system.JmeSystem

object SystemTest:

  def main(args: Array[String]) =
    val s = AppSettings(true)
    //val ar = JmeSystem.newAudioRenderer(s)
    //ar.initialize()
    //ar.cleanup()
    //val u = JmeSystem.getPlatformAssetConfigURL
    //println(u)
    val am = JmeSystem.newAssetManager()
    println(am)