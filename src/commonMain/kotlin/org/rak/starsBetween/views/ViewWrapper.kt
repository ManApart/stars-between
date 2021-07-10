package org.rak.starsBetween.views

import org.rak.starsBetween.game.ViewType

class ViewWrapper(val type: ViewType, val  data: View = type.getView())
