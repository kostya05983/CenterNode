import java.net.InetAddress
import io.netty.channel.socket.DatagramPacket as DatagramPacketNetty

fun main(args: Array<String>) {
    val clientNotify = ClientNotify(InetAddress.getByName("192.168.43.170"), 10001)
    val th = Thread(ServerNotify(10001)).start()
    clientNotify.sendNotify()
    Thread.currentThread().join()
}