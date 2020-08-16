const val tickFrequency = 200L

fun main(){
    val game = Game()
    val socket = Websocket()

    while (true) {
        game.tick()
        socket.send(SimpleFloorPlan(game.floorPlan))
        Thread.sleep(tickFrequency)
    }
}