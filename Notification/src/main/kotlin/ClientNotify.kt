import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*


class ClientNotify(private val port: Int) {
    var socket: DatagramSocket = DatagramSocket()
    init {
        socket.broadcast = true
    }

    /**
     * send notify about centerNode
     */
    fun sendNotify() {
        val buf: ByteArray = CodeNotification.Start.code.toByteArray()
        try {
            val broadcast = listAllBroadcastAddresses()
            for(remoteAddress in  broadcast) {
                val packet = DatagramPacket(buf, buf.size, remoteAddress, port)
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
    fun listAllBroadcastAddresses(): List<InetAddress> {
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