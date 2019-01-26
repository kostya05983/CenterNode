package sender.commands

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class OffCommand : SendCommand {
    override lateinit var socket: DatagramSocket
    override var port: Int = 1000
    lateinit var ip: String

    override fun execute() {
        val buffer = ByteArray(2)
        buffer[0] = CodeNotification.StopAll.code
        buffer[1] = Status.Start.code
        try {
            val packet = DatagramPacket(buffer, buffer.size, InetAddress.getByName(ip), port)
            socket.send(packet)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}