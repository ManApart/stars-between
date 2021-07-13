package ui

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.RGBA
import com.soywiz.korma.geom.vector.line

fun Container.pixel(color: RGBA, x: Int, y: Int) {
    pixel(color, x.toDouble(), y.toDouble())
}

fun Container.pixel(color: RGBA, x: Double, y: Double) {
    solidRect(1, 1, color).xy(x, y)
}