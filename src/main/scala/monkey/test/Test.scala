package monkey.test

import com.jme3.app.Application
import monkey.util.application.App

object Test:
  
  trait ApplicationImpl[A <: App] extends Application:
  
    this: A =>  
  
  def main(args: Array[String]) =
    println()
