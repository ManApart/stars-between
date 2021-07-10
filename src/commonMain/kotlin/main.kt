import com.soywiz.korge.Korge
import com.soywiz.korim.color.Colors
import ui.MainModule
import wiring.loadGame

suspend fun main() = Korge(Korge.Config(module = MainModule))
