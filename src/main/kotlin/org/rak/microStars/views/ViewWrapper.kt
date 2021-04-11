package org.rak.microStars.views

import org.rak.microStars.game.ViewType

class ViewWrapper(val type: ViewType, val  data: View = type.getView())
