import com.soywiz.korim.bitmap.Bitmap32
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

actual fun epochMillis(): Long = System.currentTimeMillis()

actual fun saveBMPToFile(bitMap: Bitmap32) {
    val data = bitMap.data.map { Color(it.r, it.g, it.b).rgb }.toIntArray()

    val image = BufferedImage(bitMap.width, bitMap.height, BufferedImage.TYPE_INT_RGB)
    image.setRGB(0,0, bitMap.width, bitMap.height, data, 0, bitMap.width)
    val res = ImageIO.write(image, "bmp", File("./screenshot.bmp"))
    println("saved $res")
}