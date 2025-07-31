package monkey.util.lwjgl.glfw

import org.lwjgl.glfw.GLFW.*
  
//  object MouseButton:
//    def apply(i: Int) = MouseButton.values.find(_.id == i).get

  enum GlfwMouseButton(val id: Int):
    case Left extends GlfwMouseButton(GLFW_MOUSE_BUTTON_LEFT)
    case Middle extends GlfwMouseButton(GLFW_MOUSE_BUTTON_MIDDLE)
    case Right extends GlfwMouseButton(GLFW_MOUSE_BUTTON_RIGHT)
