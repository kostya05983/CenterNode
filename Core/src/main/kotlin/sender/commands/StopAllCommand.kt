package sender.commands

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class StopAllCommand : SendCommand {
    override lateinit var socket: DatagramSocket
    override var port: Int = 1000
    private val ips = DistributedManager.getInstance().getArrayList<String>(DistributedKeys.Ips.name)!!

    override fun execute() {
        val buffer = ByteArray(2)
        buffer[0] = CodeNotification.StopAll.code
        buffer[1] = Status.Start.code
        try {
            for (remoteAddress in ips) {
                val packet = DatagramPacket(buffer, buffer.size, InetAddress.getByName(remoteAddress), port)
                socket.send(packet)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}