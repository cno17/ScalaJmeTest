package monkey.util.camera

import monkey.util.application.old.MonkeyApp
import monkey.util.application.simple.SimpleApp
import monkey.util.extension.MonkeyExt

trait CameraController extends MonkeyExt:

  def app: SimpleApp
  def cam = app.camera3D

  def initialize() = {}
  def update(tpf: Float) = {}
  def cleanup() = {}
