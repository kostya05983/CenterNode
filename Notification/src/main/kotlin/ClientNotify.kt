import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class ClientNotify(private val remoteAddress: InetAddress, private val port: Int) {
    var socket: DatagramSocket = DatagramSocket()

    fun sendNotify() {
        val buf: ByteArray = CodeNotification.Start.code.toByteArray()
        val packet = DatagramPacket(buf, buf.size, remoteAddress, port)

        try {
            socket.send(packet)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}