package recieve.commands

import io.netty.channel.ChannelHandlerContext
import java.net.InetAddress
import java.net.InetSocketAddress

class StartCommand(override var ctx: ChannelHandlerContext?) : RecieveCommand {
    //TODO сделать примитивный кэш, распределнный между всеми классами, можно по началу использовать обычное хранилище
    private val ips = ArrayList<InetAddress>()


    override fun execute() {
        ips.add((ctx?.channel()?.remoteAddress() as InetSocketAddress).address)
    }
}