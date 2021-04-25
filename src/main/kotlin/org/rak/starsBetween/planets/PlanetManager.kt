package org.rak.starsBetween.planets

object PlanetManager {
    val viewType = PlanetViewType.BIOME
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
