package org.rak.starsBetween.planets

import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB

class PlanetPainter {
    fun paint(planet: Planet): BufferedImage {
        val img = BufferedImage(100, 100, TYPE_INT_RGB)
        val g = img.createGraphics()

        for (x in 0 until 100) {
            for (y in 0 until 100) {
                g.color = Color.CYAN
                g.fillRect(x, y, 1, 1)
            }
        }

        g.dispose()
        return img
    }
}
