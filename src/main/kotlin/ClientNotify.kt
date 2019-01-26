import sender.commands.SendCommand
import java.net.DatagramSocket


class ClientNotify(private val port: Int) {
    var socket: DatagramSocket = DatagramSocket()
    init {
        socket.broadcast = true
    }

    /**
     * send notify about centerNode
     */
//    //TODO добавить лог
    fun sendNotify(command: SendCommand) {
        command.socket = socket
        command.port = port
        command.execute()
    }
}