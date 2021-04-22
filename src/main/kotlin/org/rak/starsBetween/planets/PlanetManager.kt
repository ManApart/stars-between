package org.rak.starsBetween.planets

object PlanetManager {
    val painter = PlanetPainter()
    private val generator = PlanetGenerator()
    private val seed = 123L
    private val planets = mutableMapOf<Int, Planet>()

    init {
        planets[0] = generator.generatePlanet(seed)
    }

    fun generatePlanet(id: Int) {
        planets[id] = generator.generatePlanet(seed)
    }

    fun getPlanet(id: Int) : Planet {
        return planets[id] ?: planets[0]!!
    }
}
