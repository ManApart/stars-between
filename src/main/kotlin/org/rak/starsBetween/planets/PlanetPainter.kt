package org.rak.starsBetween.planets

import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB

class PlanetPainter {
    fun paint(planet: Planet): BufferedImage {
        val img = BufferedImage(100, 100, TYPE_INT_RGB)
        val g = img.createGraphics()

        for (x in planet.regions.indices) {
            for (y in planet.regions.indices) {
                g.color = planet.regions[x][y].biome.color
                g.fillRect(x, y, 1, 1)
            }
        }

        g.dispose()
        return img
    }
}
