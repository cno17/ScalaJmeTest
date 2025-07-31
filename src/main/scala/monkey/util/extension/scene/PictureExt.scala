package monkey.util.extension.scene

import com.jme3.ui.Picture

trait PictureExt:

  extension (p: Picture)

    def setSize(w: Float, h: Float) =
      p.setWidth(w)
      p.setHeight(h)