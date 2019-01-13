import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.DatagramPacket
import io.netty.channel.socket.nio.NioDatagramChannel
import java.net.InetAddress
import java.net.InetSocketAddress

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

    inner class Handler : SimpleChannelInboundHandler<DatagramPacket>() {
        override fun channelRead0(ctx: ChannelHandlerContext?, msg: DatagramPacket?) {
            println("I recieved ${msg?.content().toString()}")
            ips.add((ctx?.channel()?.remoteAddress() as InetSocketAddress).address)
        }
    }
}