package monkey.test.lemur

import com.simsilica.lemur.*
import com.simsilica.lemur.component.InsetsComponent
import com.simsilica.lemur.component.SpringGridLayout
import com.simsilica.lemur.style.BaseStyles
import monkey.util.application.simple.SimpleApp


object LabelRowDemo extends SimpleApp:

  override def init() =
    // Initialize the globals so that the default components can find what they need.
    GuiGlobals.initialize(this)
    BaseStyles.loadGlassStyle()
    // Set 'glass' as the default style when not specified
    GuiGlobals.getInstance().getStyles.setDefaultStyle("glass")
    val window = Container()
    window.setLocalTranslation(100f, 400f, 0f)
    val row = window.addChild(Container(SpringGridLayout(Axis.X, Axis.Y)))
    row.addChild(newLabel("Hello", 18f))
    val button = row.addChild(Button("Click Me!"))
    button.setFontSize(30f)
    button.setBorder(InsetsComponent(16f, 8f, 32f, 8f))
    val label = row.addChild(newLabel("World", 24f))
    window.addChild(Label("Text"))
    // button.addClickCommands { println("The world is yours.")
    // label.fontSize = 40f
    // label.color = C(0.8f, 0.8f, 0.2f)
    rootNode2D.addChild(window)


  def newLabel(text: String, fontSize: Float = 14f): Label =
    val res = Label(text)
    res.setFontSize(fontSize)
    res.setTextHAlignment(HAlignment.Center)
    res.setTextVAlignment(VAlignment.Center)
    // res.preferredSize = v(w, h, 0f)
    res
