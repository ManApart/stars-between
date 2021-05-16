package org.rak.starsBetween.planet

import org.rak.starsBetween.clamp
import org.rak.starsBetween.planet.generation.PlanetOptions
import org.rak.starsBetween.planet.generation.PlanetViewOptions
import org.rak.starsBetween.views.planetView.PlanetViewType
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/planet")
class PlanetController {

    @PostMapping(path = ["/{id}"], produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generatePlanet(@PathVariable id: Int, @RequestBody options: PlanetOptions? = null) : ByteArray{
        PlanetManager.generatePlanet(id, options ?: PlanetOptions())
        return getPlanetImage(id)
    }

    @GetMapping(path = ["/{id}"], produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun getPlanetImage(@PathVariable id: Int): ByteArray {
        val image = PlanetManager.painter.paint(PlanetManager.getPlanet(id), PlanetManager.viewType)
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }

    @PutMapping(path=["/view"])
    fun setViewSettings(@RequestBody options: PlanetViewOptions) {
        PlanetManager.viewOptions = options
    }

    @GetMapping(path= ["/{id}/view"], produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun changeView(@PathVariable id: Int, @RequestParam view: PlanetViewType): ByteArray {
        PlanetManager.viewType = view
        return getPlanetImage(id)
    }

    @GetMapping(path= ["{id}/region/{x}/{y}"])
    @ResponseBody
    fun getRegion(@PathVariable id: Int, @PathVariable x: Int, @PathVariable y: Int): Region {
        val planet = PlanetManager.getPlanet(id)
        val adjustedX = clamp(x, 0, planet.regions.size-1)
        val adjustedY = clamp(y, 0, planet.regions.size-1)
        return planet.regions[adjustedX][adjustedY]
    }

    @GetMapping("/views")
    fun getViews() : List<PlanetViewType> {
        return PlanetViewType.values().toList()
    }


}
