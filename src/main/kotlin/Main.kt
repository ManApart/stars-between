const val tickFrequency = 1000L

fun main(){
    val game = Game()
    val socket = Websocket()

    while (true) {
        game.tick()
        socket.send(SimpleFloorPlan(game.floorPlan))
        Thread.sleep(tickFrequency)
    }
}