import kotlin.js.Date

actual fun epochMillis(): Long = Date.now().toLong()