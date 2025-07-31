package monkey.test.lemur

import com.simsilica.lemur.*
import com.simsilica.lemur.component.BoxLayout
import monkey.util.extension.math.MathExt

trait LemurFactory extends MathExt:

  // var
  def defaultFontSize() = 18f
  def defaultStrutWidth() = 8f
  def defaultStrutHeight() = 8f
  def labelWidth() = 100f
  def defaultHeight() = 25f

  def row(panels: Panel*): Panel =
    val res = Container(BoxLayout(Axis.X, FillMode.None))
    for panel <- panels do
      res.addChild(panel)
    res


  def button(text: String): Button =
    val res = Button(text)
    res.setPreferredSize(V(labelWidth(), defaultHeight(), 0f))
    res

  def hglue(): Panel =
    val res = Label("") // Panel(w, 1f, C(0.2f, 0.8f, 0.2f))
    res.setPreferredSize(V(1000f, 1f, 0f))
    res


  def hstrut(w: Float = defaultStrutWidth()): Panel =
    val res = Label("") // Panel(w, 1f, C(0.2f, 0.8f, 0.2f))
    res.setPreferredSize(V(w, 1f, 0f))
    res


  def vstrut(h: Float = defaultStrutHeight()): Panel =
    val res = Label("")
    res.setPreferredSize(V(1f, h, 0f))
    res


  def label(text: String): Label =
    val res = Label(text)
    res.setFontSize(defaultFontSize())
    res.setTextHAlignment(HAlignment.Left)
    res.setTextVAlignment(VAlignment.Center)
    res.setPreferredSize(V(labelWidth(), defaultHeight(), 0f))
    res


  def slider(min: Double, max: Double, cur: Double, w: Float = 200f): Slider =
    val res = Slider(DefaultRangedValueModel(min, max, cur), Axis.X)
    res.setPreferredSize(V(w, defaultHeight(), 0f))
    res


//  def spinner(): Spinner[Double] =
//    val res = Spinner(SequenceModels.DoubleSequence(5.0, 1.0, 1.0))
//    res.setPreferredSize(v(100f, defaultHeight(), 0f))
//    res
    


