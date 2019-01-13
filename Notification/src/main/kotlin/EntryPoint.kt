import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.InetSocketAddress
import io.netty.channel.socket.DatagramPacket as DatagramPacketNetty


enum class CodeNotification(val code: String) {
    Start("0");
}

fun main(args: Array<String>) {
    val clientNotify = ClientNotify(InetAddress.getByName("192.168.43.170"), 10001)
    val th = Thread(ServerNotify(10001)).start()
    clientNotify.sendNotify()
    Thread.currentThread().join()
}

//ClientPart
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

//Server part
class ServerNotify(private val port: Int) : Runnable {
    private val ips = ArrayList<InetAddress>()

    override fun run() {
        val workerGroup = NioEventLoopGroup()//так называемая группа событий, используемая при создании каналов между серверами и клиентом
        try {
            val bs = Bootstrap()
            bs.group(workerGroup).channel(NioDatagramChannel::class.java)//говорим серверу о том, какой типа канала используется для общения. Тут он является наследником от Channel
                    .handler(getChanel())
            bs.bind(port).sync().channel().closeFuture()
        } finally {
            workerGroup.shutdownGracefully()
        }
    }

    private fun getChanel() = object : ChannelInitializer<NioDatagramChannel>() {
        override fun initChannel(ch: NioDatagramChannel?) {
            ch?.pipeline()?.addLast(Handler())
        }
    }

    inner class Handler : SimpleChannelInboundHandler<DatagramPacketNetty>() {
        override fun channelRead0(ctx: ChannelHandlerContext?, msg: DatagramPacketNetty?) {
            println("I recieved ${msg?.content().toString()}")
            ips.add((ctx?.channel()?.remoteAddress() as InetSocketAddress).address)
        }
    }
}