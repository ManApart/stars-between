package org.rak.starsBetween.planets

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/planet")
class PlanetController {
    val generator = PlanetGenerator()

    fun generatePlanet(seed: Long = 1234, density: Int = 100, scale: Float = 1f, id: Int? = null) {
        generator.generatePlanet(seed)
    }

    @GetMapping(produces = [MediaType.IMAGE_JPEG_VALUE])
    @ResponseBody
    fun getPlanetImage(): ByteArray {
        val image = PlanetManager.painter.paint(PlanetManager.planet)
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "jpg", baos)
        return baos.toByteArray()
    }


}
