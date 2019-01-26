package sender.commands

import java.net.DatagramSocket

interface SendCommand {
    var socket: DatagramSocket
    var port: Int

    fun execute()
}