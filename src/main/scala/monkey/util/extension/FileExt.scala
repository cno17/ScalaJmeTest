package monkey.util.extension

import java.io.File
import java.io.FileFilter
import java.io.FileInputStream
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

// todo: return Options
// def getChildren(f: File) = if f.isFile then None else Some(f.listFiles())

trait FileExt:

  extension (f: File)

    def isFile = !f.isDirectory

    def getShortName =
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory || i == -1 then s else s.substring(0, i)

    def getExtension =
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory || i == -1 then "" else s.substring(i + 1)

    def getParent = f.getParentFile()

    def getChildren = f.listFiles()

    def getDescendants = getDescendants2(f, ArrayBuffer[File]()).toArray

    def getBytes =
      val fis = FileInputStream(f)
      val res = fis.readAllBytes()
      fis.close()
      res

    def getChars =
      val src = Source.fromFile(f)
      val res = src.getLines.mkString("\n")
      src.close()
      res

    def getLines =
      val src = Source.fromFile(f)
      val res = src.getLines.toArray
      src.close()
      res

  private def getDescendants2(f: File, res: ArrayBuffer[File]): ArrayBuffer[File] =
    if f.isDirectory then
      res ++= f.listFiles()
      for d <- f.listFiles(_.isDirectory) do
        getDescendants2(d, res)
    res
