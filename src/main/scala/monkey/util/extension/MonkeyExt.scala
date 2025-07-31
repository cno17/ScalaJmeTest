package monkey.util.extension

import monkey.util.extension.asset.AssetManagerExt
import monkey.util.extension.bullet.BulletExt
import monkey.util.extension.math.MathExt
import monkey.util.extension.renderer.RendererExt
import monkey.util.extension.scene.SceneExt
import monkey.util.extension.terrain.TerrainExt

trait MonkeyExt extends 
  AppExt, 
  AssetManagerExt,
  BulletExt, 
  LightExt, 
  MathExt, 
  RendererExt, 
  SceneExt, 
  TerrainExt
