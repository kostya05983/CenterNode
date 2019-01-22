import io.netty.channel.socket.DatagramPacket as DatagramPacketNetty

fun main(args: Array<String>) {
    val clientNotify = ClientNotify( 10001)
    val th = Thread(ServerNotify(10001)).start()
    clientNotify.sendNotify()
    Thread.currentThread().join()
}