package monkey.test.util

import monkey.util.extension.FileExt
import java.io.File

object FileExtTest extends FileExt:

  def main(args: Array[String]) =
    val root = File("c://users/hank/cs/java/jme/jme-3.6.1/jme-c/src/main/java")
    val dir = File(root, "com/jme3/app")
    for f <- dir.getDescendants do
      println(s"${f.getShortName}: ${f.isDirectory}")
