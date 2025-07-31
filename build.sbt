ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion := "3.6.3"

lazy val root = (project in file(".")).settings(name := "ScalaJmeTest")

libraryDependencies += "org.jmonkeyengine" % "jme3-awt-dialogs" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-core" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-desktop" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-effects" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-jbullet" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-jogg" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-lwjgl3" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-plugins" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-plugins-json" % "3.7.0-stable"
libraryDependencies += "org.jmonkeyengine" % "jme3-terrain" % "3.7.0-stable"

libraryDependencies += "com.github.stephengold" % "jme-ttf" % "3.0.1"
libraryDependencies += "com.simsilica" % "lemur" % "1.16.0"
libraryDependencies += "com.simsilica" % "lemur-proto" % "1.13.0"

libraryDependencies += "org.lwjgl" % "lwjgl" % "3.3.3"
libraryDependencies += "org.lwjgl" % "lwjgl" % "3.3.3" classifier "natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-glfw" % "3.3.3"
libraryDependencies += "org.lwjgl" % "lwjgl-glfw" % "3.3.3" classifier "natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-par" % "3.3.3"
libraryDependencies += "org.lwjgl" % "lwjgl-par" % "3.3.3" classifier "natives-windows"

libraryDependencies += "org.locationtech.jts" % "jts-core" % "1.20.0"

// libraryDependencies += "io.github.earcut4j" % "earcut4j" % "2.2.2"

libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.16"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.16"

// libraryDependencies += "org.apache.groovy" % "groovy-all" % "4.0.17"
// libraryDependencies += "org.jmonkeyengine:jme3-jbullet:$jmeVersion")
// libraryDependencies += "com.sudoplay.joise" % joise:1.1.0")