package sender.commands

import java.net.DatagramSocket

class StopAllCommand: SendCommand {
    override lateinit var socket: DatagramSocket
    override var port: Int = 1000

    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}