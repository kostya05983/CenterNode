import java.util.concurrent.ConcurrentHashMap

//how to save objects? Maps, but there are will a lot of maps...
object DistributedManager {
    val maps: ConcurrentHashMap<String, DMap<*, *>> = ConcurrentHashMap()
    val arrays: ConcurrentHashMap<String, DArray<*>> = ConcurrentHashMap()

    fun getMap(key: String): DMap<*, *>? = maps[key]

    fun getArrayList(key: String): DArray<*>? = arrays[key]
}