package ui.planetScene.planetView

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.circle
import com.soywiz.korge.view.clipContainer
import com.soywiz.korge.view.graphics
import com.soywiz.korim.color.RGBA
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

    //    if (PlanetManager.viewOptions.sphere) {
    paintSphereOverlay(planet.regions.size)
//    }

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
//            val size = planet.regions.size
//            val offset = -size / 10f
//            g.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)
//            g.color = Color.BLACK
//
//            val shadow = Area(Ellipse2D.Float(0f, 0f, size.toFloat(), size.toFloat()))
//            val cutOut = Area(Ellipse2D.Float(offset, offset, size.toFloat(), size.toFloat()))
//
//            shadow.subtract(cutOut)
//
//            g.fill(shadow)
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
//    val r = size / 2.toDouble()
//    circle(r, fill = RGBA(0, 0), strokeThickness = 20.0)
//        val ellipse = Ellipse2D.Float(0f, 0f, size.toFloat(), size.toFloat())
//        g.clip(ellipse)
}

