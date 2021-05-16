package org.rak.starsBetween.views.planetView

import org.rak.starsBetween.planet.Planet
import org.rak.starsBetween.planet.PlanetManager
import org.rak.starsBetween.planet.generation.PlanetViewOptions
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Area
import java.awt.geom.Ellipse2D
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB


class PlanetPainter {
    fun paint(planet: Planet, type: PlanetViewType): BufferedImage {
        val img = BufferedImage(planet.regions.size, planet.regions.size, TYPE_INT_RGB)
        val g = img.createGraphics()!!

        if (PlanetManager.viewOptions.sphere) {
            paintSphereOverlay(planet.regions.size, g)
        }

        when (type) {
            PlanetViewType.ALTITUDE -> paintAltitude(planet, g)
            PlanetViewType.PRECIPITATION -> paintPrecipitation(planet, g)
            PlanetViewType.TEMPERATURE -> paintTemperature(planet, g)
            PlanetViewType.SATELLITE -> paintSatellite(planet, g)
            else -> paintBiomes(planet, g, PlanetManager.viewOptions)
        }

        g.dispose()
        return img
    }

    private fun paintAltitude(planet: Planet, g: Graphics2D) {
        for (x in planet.regions.indices) {
            for (y in planet.regions.indices) {
                val altitude = planet.regions[x][y].altitude
                g.color = altitudeSpectrum.getColor(altitude)
                g.fillRect(x, y, 1, 1)
            }
        }
    }

    private fun paintBiomes(planet: Planet, g: Graphics2D, options: PlanetViewOptions) {
        for (x in planet.regions.indices) {
            for (y in planet.regions.indices) {
                g.color = planet.regions[x][y].biome.color
                g.fillRect(x, y, 1, 1)
            }
        }

        if (options.shadow) {
            val size = planet.regions.size
            val offset = -size / 10f
            g.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)
            g.color = Color.BLACK

            val shadow = Area(Ellipse2D.Float(0f, 0f, size.toFloat(), size.toFloat()))
            val cutOut = Area(Ellipse2D.Float(offset, offset, size.toFloat(), size.toFloat()))

            shadow.subtract(cutOut)

            g.fill(shadow)
        }
    }

    private fun paintPrecipitation(planet: Planet, g: Graphics2D) {
        for (x in planet.regions.indices) {
            for (y in planet.regions.indices) {
                val precipitation = planet.regions[x][y].precipitation
                g.color = precipitationSpectrum.getColor(precipitation)
                g.fillRect(x, y, 1, 1)
            }
        }
    }

    private fun paintTemperature(planet: Planet, g: Graphics2D) {
        for (x in planet.regions.indices) {
            for (y in planet.regions.indices) {
                val temperature = planet.regions[x][y].temperature
                g.color = temperatureSpectrum.getColor(temperature)
                g.fillRect(x, y, 1, 1)
            }
        }
    }

    private fun paintSatellite(planet: Planet, g: Graphics2D) {
        for (x in planet.regions.indices) {
            for (y in planet.regions.indices) {
                val altitude = planet.regions[x][y].temperature
                g.color = satelliteSpectrum.getColor(altitude)
                g.fillRect(x, y, 1, 1)
            }
        }
    }

    private fun paintSphereOverlay(size: Int, g: Graphics2D) {
        val ellipse = Ellipse2D.Float(0f, 0f, size.toFloat(), size.toFloat())
        g.clip(ellipse)
    }

}
