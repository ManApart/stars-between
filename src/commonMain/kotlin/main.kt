import com.soywiz.korge.Korge
import com.soywiz.korim.color.Colors
import wiring.loadGame

const val gameTickFrequency = 200L
const val broadcastFrequency = 200L

suspend fun main() = Korge(width = 480, height = 640, title = "Stars Between", bgcolor = Colors.BLACK) {
//    val font = resourcesVfs["clear_sans.fnt"].read
    loadGame()

//    fixedRateTimer("gametick", true, 0L, gameTickFrequency){
//        Game.tick()
//    }
//
//    fixedRateTimer("broadcast", true, 0L, broadcastFrequency){
//        SocketManager.socket.send(ViewWrapper(Game.currentView))
//    }

}
