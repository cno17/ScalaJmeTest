package monkey.test.gis.jts

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.triangulate.polygon.PolygonTriangulator

import scala.jdk.CollectionConverters.*

object PolygonTriangulationTest:

  def main(args: Array[String]) =
    val gf = GeometryFactory()
    val p0 = Coordinate(0, 0)
    val p1 = Coordinate(2, 0)
    val p2 = Coordinate(3, 1)
    val p3 = Coordinate(3, 3)
    val p4 = Coordinate(1, 3)
    val shell = Array(p0, p1, p2, p3, p4, p0)
    val polygon = gf.createPolygon(shell)
    val tris = PolygonTriangulator(polygon).getTriangles.asScala
    for t <- tris do
      println(t)
