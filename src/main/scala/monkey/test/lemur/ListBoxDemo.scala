package monkey.test.lemur

import com.simsilica.lemur.*
import com.simsilica.lemur.style.BaseStyles
import monkey.util.application.simple.SimpleApp

/**
 * TODO: selection listener, font size, cell renderer, ...
 */

object ListBoxDemo extends SimpleApp:

  override def init() =
    // Initialize the globals so that the default components can find what they need.
    GuiGlobals.initialize(this)
    BaseStyles.loadGlassStyle()
    // Set 'glass' as the default style when not specified
    GuiGlobals.getInstance.getStyles.setDefaultStyle("glass")
    // Create a simple container for our elements
    val window = Container()
    window.setLocalTranslation(100f, 400f, 0f)
    window.addChild(Label("List Box Demo"))
    val list = window.addChild(ListBox[String]())
    println(list.getModel.size)
    list.setVisibleItems(3)
    list.getModel().add("Red")
    list.getModel().add("Green")
    list.getModel().add("Blue")
    list.getModel().add("White")
    list.getModel().add("Black")
    val button = window.addChild(Button("Click Me"))
    button.setPreferredSize(V(400f, 40f, 0f))
    button.setFontSize(20f)
    button.setTextHAlignment(HAlignment.Center)
    button.setTextVAlignment(VAlignment.Center)
    // button.addClickCommands { println("The world is yours.") }
    rootNode2D.addChild(window)
