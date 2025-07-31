package monkey.util.extension.scene

import com.jme3.scene.Geometry
import com.jme3.scene.Mesh
import com.jme3.util.TangentBinormalGenerator
import monkey.util.material.SimpleMaterial

trait GeometryExt:
  
  def Geometry(name: String, mesh: Mesh, material: SimpleMaterial, tangents: Boolean) =
    if tangents then TangentBinormalGenerator.generate(mesh)
    val res = new Geometry(name, mesh)
    if material != null then res.setMaterial(material.delegate)
    res

  def Geometry(mesh: Mesh, material: SimpleMaterial, tangents: Boolean): Geometry =
    Geometry("", mesh, material, tangents)

  def Geometry(mesh: Mesh, material: SimpleMaterial): Geometry =
    Geometry("", mesh, material, false)

  def Geometry(mesh: Mesh): Geometry =
    Geometry("", mesh, null, false)

  
  // drop theese
    
//   def Geometry(name: String, mesh: Mesh, material: SimpleMaterial, tangents: Boolean) =
//     if tangents then TangentBinormalGenerator.generate(mesh)
//     val res = new Geometry(name, mesh)
//     res.setMaterial(material.delegate)
//     res
//
//   def Geometry(mesh: Mesh, material: SimpleMaterial, tangents: Boolean): Geometry =
//     Geometry("", mesh, material, tangents)
//
//   def Geometry(mesh: Mesh, material: SimpleMaterial): Geometry =
//     Geometry("", mesh, material, false)

  extension (g: Geometry)
    
    def createTangents() = TangentBinormalGenerator.generate(g.getMesh)
    def setMaterial(m: SimpleMaterial) = g.setMaterial(m.delegate)