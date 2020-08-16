import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress

val url = "localhost"
val port = 1235

class Websocket : WebSocketServer(InetSocketAddress(url, port)) {
    private val mapper = jacksonObjectMapper()

    init {
        startUp()
    }

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        println("Websocket Open")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        println("Websocket Closed")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        println("Websocket On Message")
    }

    override fun onStart() {
        println("Websocket On Start")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        println("Websocket errored")
    }

    fun send(floorPlan: SimpleFloorPlan) {
        val json = mapper.writeValueAsString(floorPlan)
        broadcast(json)
    }

    private fun startUp(){
        Thread {
            run()
        }.start()

        Runtime.getRuntime().addShutdownHook(Thread{
            stop(1000)
            Thread.sleep(500)
            println("Stopped Websocket")
        })
    }

}