package planet.generation

import planet.BiomeType
import ui.planetScene.planetView.PlanetViewType

class PlanetOptionsUI(private val updated: (PlanetOptionsUI) -> Unit = { println("Updated") }) {
    var autoUpdate = true

    var viewType: PlanetViewType = PlanetViewType.BIOME
        set(value) {
            field = value; update()
        }

    var sphere: Boolean = true
        set(value) {
            field = value; update()
        }

    var shadow: Boolean = true
        set(value) {
            field = value; update()
        }

    var seed: Double = 1234.0
        set(value) {
            field = value; update()
        }
    var density: Double = 100.0
        set(value) {
            field = value; update()
        }
    var biomeType: BiomeType = BiomeType.EARTH_LIKE
        set(value) {
            field = value; update()
        }

    //heightmap
    var octaves: Double = 7.0
        set(value) {
            field = value; update()
        }
    var roughness: Double = 0.5
        set(value) {
            field = value; update()
        }
    var noiseScale: Double = 7.0
        set(value) {
            field = value; update()
        }

    //temperature
    var temperature: Double = 50.0
        set(value) {
            field = value; update()
        }
    var temperatureVariance: Double = 50.0
        set(value) {
            field = value; update()
        }
    var temperatureFactor: Double = 1.4
        set(value) {
            field = value; update()
        }

    //precipitation
    var defaultPrecipitation: Double = 100.0
        set(value) {
            field = value; update()
        }

    var waterThreshold: Double = 0.0
        set(value) {
            field = value; update()
        }
    
    private fun update() {
        if (autoUpdate) updated(this)
    }

    fun toOptions(): PlanetOptions {
        return PlanetOptions(
            seed.toLong(),
            density.toInt(),
            octaves.toInt(),
            roughness.toFloat(),
            noiseScale.toFloat(),
            temperature.toInt(),
            temperatureVariance.toInt(),
            temperatureFactor,
            defaultPrecipitation.toInt(),
            waterThreshold.toInt(),
            biomeType
        )
    }

    fun toViewOptions(): PlanetViewOptions {
        return PlanetViewOptions(viewType, sphere, shadow)
    }
}
