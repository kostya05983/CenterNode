package recieve.commands

import io.netty.channel.ChannelHandlerContext

interface RecieveCommand {
    var ctx: ChannelHandlerContext?
    fun execute()
}