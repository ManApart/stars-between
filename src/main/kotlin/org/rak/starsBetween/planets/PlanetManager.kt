package org.rak.starsBetween.planets

import org.rak.starsBetween.planets.generation.PlanetGenerator
import org.rak.starsBetween.planets.generation.PlanetOptions
import org.rak.starsBetween.views.planetView.PlanetPainter
import org.rak.starsBetween.views.planetView.PlanetViewType

object PlanetManager {
    val viewType = PlanetViewType.ALTITUDE
    val painter = PlanetPainter()
    private val generator = PlanetGenerator()
    private val planets = mutableMapOf<Int, Planet>()

    init {
        planets[0] = generator.generatePlanet(PlanetOptions())
    }

    fun generatePlanet(id: Int, planetOptions: PlanetOptions) {
        planets[id] = generator.generatePlanet(planetOptions)
    }

    fun getPlanet(id: Int) : Planet {
        return planets[id] ?: planets[0]!!
    }
}
