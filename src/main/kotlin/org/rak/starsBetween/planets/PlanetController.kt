package org.rak.starsBetween.planets

import org.rak.starsBetween.clamp
import org.rak.starsBetween.planets.generation.PlanetOptions
import org.rak.starsBetween.views.planetView.PlanetViewType
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/planet/{id}")
class PlanetController {

    @PostMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun generatePlanet(@PathVariable id: Int, @RequestBody options: PlanetOptions? = null) : ByteArray{
        PlanetManager.generatePlanet(id, options ?: PlanetOptions())
        return getPlanetImage(id)
    }

    @GetMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getPlanetImage(@PathVariable id: Int): ByteArray {
        val image = PlanetManager.painter.paint(PlanetManager.getPlanet(id ?: 0), PlanetManager.viewType)
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }

    @GetMapping(path= ["/view"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun changeView(@PathVariable id: Int, @RequestParam view: PlanetViewType): ByteArray {
        PlanetManager.viewType = view
        return getPlanetImage(id)
    }

    @GetMapping(path= ["/region/{x}/{y}"])
    @ResponseBody
    fun getRegion(@PathVariable id: Int, @PathVariable x: Int, @PathVariable y: Int): Region {
        val planet = PlanetManager.getPlanet(id)
        val adjustedX = clamp(x, 0, planet.regions.size)
        val adjustedY = clamp(y, 0, planet.regions.size)
        return planet.regions[adjustedX][adjustedY]
    }


}
