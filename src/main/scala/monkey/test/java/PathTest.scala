package monkey.test.java

import java.nio.file.Path

object PathTest:

  def main(args: Array[String]) =
    val p = Path.of("").toAbsolutePath.getParent.resolve("JmeData")
    println(p)
    println(p.toFile.exists())
