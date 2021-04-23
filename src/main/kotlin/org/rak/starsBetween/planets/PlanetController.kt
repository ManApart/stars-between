package org.rak.starsBetween.planets

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
    fun generatePlanet(id: Int? = null, options: PlanetOptions? = null) : ByteArray{
        PlanetManager.generatePlanet(id ?: 0, options ?: PlanetOptions())
        return getPlanetImage(id)
    }

    @GetMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getPlanetImage(id: Int? = null): ByteArray {
        val image = PlanetManager.painter.paint(PlanetManager.getPlanet(id ?: 0))
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "png", baos)
        return baos.toByteArray()
    }


}
