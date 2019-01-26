package recieve.commands

import io.netty.channel.ChannelHandlerContext
import java.net.InetSocketAddress

class JoinCommand(override var ctx: ChannelHandlerContext?) : RecieveCommand {
    private val ips = DistributedManager.getInstance().getArrayList<String>(DistributedKeys.Ips.name)!!


    override fun execute() {
        ips.add((ctx?.channel()?.remoteAddress() as InetSocketAddress).address.hostAddress)
    }
}