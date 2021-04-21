package org.rak.starsBetween.planets

import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

import org.springframework.web.bind.annotation.ResponseBody
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/planet")
class PlanetController {

    fun generatePlanet(seed: Long, density: Int, scale: Float, id: Int?) {

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
