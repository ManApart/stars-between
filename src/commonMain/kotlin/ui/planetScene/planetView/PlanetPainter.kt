package ui.planetScene.planetView

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.ellipse
import com.soywiz.korma.geom.vector.rect
import planet.Planet
import planet.generation.PlanetViewOptions
import ui.pixel
import ui.planetScene.PlanetManager


fun Container.paint(planet: Planet, type: PlanetViewType) {
    when (type) {
        PlanetViewType.ALTITUDE -> paintAltitude(planet)
        PlanetViewType.PRECIPITATION -> paintPrecipitation(planet)
        PlanetViewType.TEMPERATURE -> paintTemperature(planet)
        PlanetViewType.SATELLITE -> paintSatellite(planet)
        else -> paintBiomes(planet, PlanetManager.viewOptions)
    }

    if (PlanetManager.viewOptions.sphere) {
        paintSphereOverlay(planet.regions.size)
    }

}

private fun Container.paintAltitude(planet: Planet) {
    for (x in planet.regions.indices) {
        for (y in planet.regions.indices) {
            val altitude = planet.regions[x][y].altitude
            val color = altitudeSpectrum.getColor(altitude)
            pixel(color, x, y)
        }
    }
}

private fun Container.paintBiomes(planet: Planet, options: PlanetViewOptions) {
    for (x in planet.regions.indices) {
        for (y in planet.regions.indices) {
            val color = planet.regions[x][y].biome.color
            pixel(color, x, y)
        }
    }

    if (options.shadow) {
        val size = planet.regions.size
        val offset = -size / 10f
        graphics {
            fill(Colors.BLACK, 0.5) {}
            ellipse(0, 0, size, size)
            ellipse(offset, offset, size.toFloat(), size.toFloat())
            endFill()
        }
    }
}

private fun Container.paintPrecipitation(planet: Planet) {
    for (x in planet.regions.indices) {
        for (y in planet.regions.indices) {
            val precipitation = planet.regions[x][y].precipitation
            val color = precipitationSpectrum.getColor(precipitation)
            pixel(color, x, y)
        }
    }
}

private fun Container.paintTemperature(planet: Planet) {
    for (x in planet.regions.indices) {
        for (y in planet.regions.indices) {
            val temperature = planet.regions[x][y].temperature
            val color = temperatureSpectrum.getColor(temperature)
            pixel(color, x, y)
        }
    }
}

private fun Container.paintSatellite(planet: Planet) {
    for (x in planet.regions.indices) {
        for (y in planet.regions.indices) {
            val altitude = planet.regions[x][y].temperature
            val color = satelliteSpectrum.getColor(altitude)
            pixel(color, x, y)
        }
    }
}

private fun Container.paintSphereOverlay(size: Int) {
    graphics {
        fill(Colors.BLACK) {}
        rect(0, 0, size + 1, size + 1)
        ellipse(0, 0, size, size)
        endFill()
    }
}

