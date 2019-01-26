package recieve.commands

import io.netty.channel.ChannelHandlerContext
import sun.plugin2.applet.ManagerCache
import java.net.InetAddress
import java.net.InetSocketAddress

class StartCommand(override var ctx: ChannelHandlerContext?) : RecieveCommand {
    private val ips = DistributedManager.getInstance().getArrayList<String>(DistributedKeys.Ips.name)!!

    override fun execute() {
        ips.add((ctx?.channel()?.remoteAddress() as InetSocketAddress).address.hostAddress)
    }
}