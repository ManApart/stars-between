package crew

import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA

enum class Division(val color: RGBA) {
    SCIENCE(Colors.BLUE),
    ENGINEERING(Colors.GREEN),
    COMBAT(Colors.RED),
    COMMAND(Colors.YELLOW)
}