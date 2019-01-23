package sender.commands

import CodeNotification
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*

class StartCommand: SendCommand {
    override lateinit var socket: DatagramSocket
    override var port: Int = 1000

    override fun execute() {
        val buffer = ByteArray(2)
        buffer[0] = CodeNotification.Start.code
        buffer[1] = Status.Start.code
        try {
            val broadcast = listAllBroadcastAddresses()
            for(remoteAddress in  broadcast) {
                val packet = DatagramPacket(buffer, buffer.size, remoteAddress,port)
                socket.send(packet)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Collect all addresses of networkInterfaces with gateway
     * send broadcast packet
     */
    private fun listAllBroadcastAddresses(): List<InetAddress> {
        val broadcastList = ArrayList<InetAddress>()
        val interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement()

            if (networkInterface.isLoopback || !networkInterface.isUp) {
                continue
            }

            networkInterface.interfaceAddresses.stream()
                    .map { a -> a.broadcast }
                    .filter { Objects.nonNull(it) }
                    .forEach { broadcastList.add(it) }
        }
        return broadcastList
    }
}