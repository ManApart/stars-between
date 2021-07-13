package ui

import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA

fun Container.pixel(color: RGBA, x: Int, y: Int) {
    pixel(color, x.toDouble(), y.toDouble())
}

fun Container.pixel(color: RGBA, x: Double, y: Double) {
    solidRect(1, 1, color).xy(x, y)
}

fun Container.button(text: String, callback: () -> Unit = {}, color: RGBA = Colors.PINK): View {
    return roundRect(150.0, 40.0, 1.0, fill = Colors.BEIGE)
}