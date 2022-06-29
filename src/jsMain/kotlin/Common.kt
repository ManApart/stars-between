import com.soywiz.korim.bitmap.Bitmap32
import kotlin.js.Date

actual fun epochMillis(): Long = Date.now().toLong()

actual fun saveBMPToFile(bitMap: Bitmap32) {
    println("No Save to file on web")
}