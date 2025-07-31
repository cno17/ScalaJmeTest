package monkey.test.lemur

import com.simsilica.lemur.*
import com.simsilica.lemur.style.BaseStyles
import monkey.util.application.simple.SimpleApp

object GuiDemo extends SimpleApp:

  override def init() =
    // Initialize the globals so that the default components can find what they need.
    GuiGlobals.initialize(this)
    BaseStyles.loadGlassStyle()
    // Set 'glass' as the default style when not specified
    GuiGlobals.getInstance().getStyles.setDefaultStyle("glass")
    // Create a simple container for our elements
    val window = Container()
    window.getSize.set(400f, 300f, 1f)
    // Put it somewhere that we will see it. Lemur GUI elements grow up from the lower left corner.
    window.setLocalTranslation(100f, 400f, 0f)
    // window.setLocalScale(2f, 2f, 1f)
    // Add some elements
    val label = window.addChild(Label("Hello, World."))
    label.setFontSize(40f)
    label.setColor(C(0.8f, 0.8f, 0.2f))
    val slider = window.addChild(Slider(DefaultRangedValueModel(0.0, 100.0, 50.0), Axis.X))
    slider.setPreferredSize(V(200f, 40f, 0f))
    val button = window.addChild(Button("Click Me"))
    button.setPreferredSize(V(400f, 80f, 0f))
    button.setFontSize(30f)
    button.setTextHAlignment(HAlignment.Center)
    button.setTextVAlignment(VAlignment.Center)
    // todo button.addClickCommands { println("The world is yours.") }
    rootNode2D.addChild(window)
