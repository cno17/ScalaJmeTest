package monkey.util.extension.asset

import com.jme3.`export`.binary.BinaryLoader
import com.jme3.asset.AssetManager
import com.jme3.audio.plugins.OGGLoader
import com.jme3.audio.plugins.WAVLoader
import com.jme3.cursors.plugins.CursorLoader
import com.jme3.font.plugins.BitmapFontLoader
import com.jme3.material.plugins.J3MLoader
import com.jme3.material.plugins.ShaderNodeDefinitionLoader
import com.jme3.scene.plugins.*
import com.jme3.scene.plugins.MTLLoader
import com.jme3.scene.plugins.OBJLoader
import com.jme3.scene.plugins.fbx.FbxLoader
import com.jme3.shader.plugins.GLSLLoader
import com.jme3.texture.plugins.AWTLoader
import com.jme3.texture.plugins.DDSLoader
import com.jme3.texture.plugins.HDRLoader
import com.jme3.texture.plugins.PFMLoader
import com.jme3.texture.plugins.TGALoader

trait AssetManagerExt:
  
  extension (res: AssetManager)
    
    def initLoaders() =
      res.registerLoader(classOf[AWTLoader], "jpg", "gif", "png")
      res.registerLoader(classOf[BitmapFontLoader], "fnt")
      res.registerLoader(classOf[BinaryLoader], "j3o", "j3f")
      res.registerLoader(classOf[CursorLoader], "ani", "cur", "ico")
      res.registerLoader(classOf[DDSLoader], "dds")
      res.registerLoader(classOf[FbxLoader], "fbx")
      res.registerLoader(classOf[GLSLLoader], "vert", "frag", "geom", "tsctrl", "tseval", "glsl", "glsllib")
      res.registerLoader(classOf[HDRLoader], "hdr")
      res.registerLoader(classOf[J3MLoader], "j3m", "j3md")
      res.registerLoader(classOf[MTLLoader], "mtl")
      res.registerLoader(classOf[OBJLoader], "obj")
      res.registerLoader(classOf[OGGLoader], "ogg")
      res.registerLoader(classOf[PFMLoader], "pfm")
      res.registerLoader(classOf[ShaderNodeDefinitionLoader], "j3sn")
      res.registerLoader(classOf[TGALoader], "tga")
      res.registerLoader(classOf[WAVLoader], "wav")
      //
      res.registerLoader(classOf[gltf.BinLoader], "bin")
      res.registerLoader(classOf[gltf.GlbLoader], "glb")
      res.registerLoader(classOf[gltf.GltfLoader], "gltf")
      res.registerLoader(classOf[ogre.MaterialLoader], "material")
      res.registerLoader(classOf[ogre.MeshLoader], "mesh.xml")
      res.registerLoader(classOf[ogre.SceneLoader], "scene")
      res.registerLoader(classOf[ogre.SkeletonLoader], "skeleton.xml")
