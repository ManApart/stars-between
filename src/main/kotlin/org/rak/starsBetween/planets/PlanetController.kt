package org.rak.starsBetween.planets

import org.rak.starsBetween.planets.generation.PlanetOptions
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/planet")
class PlanetController {

    @PostMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun generatePlanet(@RequestParam id: Int? = null, @RequestBody options: PlanetOptions? = null) : ByteArray{
        PlanetManager.generatePlanet(id ?: 0, options ?: PlanetOptions())
        return getPlanetImage(id)
    }

    @GetMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getPlanetImage(id: Int? = null): ByteArray {
        val image = PlanetManager.painter.paint(PlanetManager.getPlanet(id ?: 0), PlanetManager.viewType)
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }


}
