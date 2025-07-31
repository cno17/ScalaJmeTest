package monkey.test.lemur

import com.simsilica.lemur.Container
import com.simsilica.lemur.GuiGlobals
import com.simsilica.lemur.component.SpringGridLayout
import com.simsilica.lemur.style.BaseStyles
import monkey.util.application.simple.SimpleApp

object LayoutDemo extends SimpleApp, LemurFactory:

  override def init() =
    GuiGlobals.initialize(this)
    BaseStyles.loadGlassStyle()
    GuiGlobals.getInstance().getStyles.setDefaultStyle("glass")
    val container = Container(SpringGridLayout())
    container.setPreferredSize(V(600f, 50f, 0f))
    container.setLocalTranslation(100f, 600f, 0f)
    container.addChild(label("A"))
    container.addChild(label("B"))
    container.addChild(label("C"))
    rootNode2D.addChild(container)
