package ui.planetScene

import planet.Planet
import planet.generation.PlanetGenerator
import planet.generation.PlanetOptions
import planet.generation.PlanetViewOptions
import ui.planetScene.planetView.PlanetViewType

object PlanetManager {
    var viewType = PlanetViewType.BIOME
    var viewOptions = PlanetViewOptions()
    private val generator = PlanetGenerator()
    private val planets = mutableMapOf<Int, Planet>()

    fun generatePlanet(id: Int, planetOptions: PlanetOptions) {
        planets[id] = generator.generatePlanet(planetOptions)
    }

    fun getPlanet(id: Int): Planet {
        return planets.getOrPut(id) { generator.generatePlanet(PlanetOptions()) }
    }
}
