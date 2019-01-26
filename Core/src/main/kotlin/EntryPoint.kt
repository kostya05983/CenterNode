import sender.commands.StartCommand
import io.netty.channel.socket.DatagramPacket as DatagramPacketNetty

fun main(args: Array<String>) {
    //init manager
    val settingsBuilder = SettingsBuilderImpl()
//    settingsBuilder.arrays
    settingsBuilder.addSource(DistributedKeys.Ips.name, NullListProvider::class)

    val clientNotify = ClientNotify(10001)
    val th = Thread(ServerNotify(10001)).start()
    clientNotify.sendNotify(StartCommand())
    Thread.currentThread().join()

}