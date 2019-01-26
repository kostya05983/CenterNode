import java.util.concurrent.ConcurrentHashMap

//how to save objects? Maps, but there are will a lot of maps...
class DistributedManager {
    private val maps: ConcurrentHashMap<String, DMap<*, *>> = ConcurrentHashMap()
    private val arrays: ConcurrentHashMap<String, DArray<*>> = ConcurrentHashMap()

    fun getMap(key: String): DMap<*, *>? = maps[key]

    fun<V> getArrayList(key: String): DArray<V>? = arrays[key] as DArray<V>

    companion object {
        private lateinit var instance: DistributedManager

        fun getInstance(): DistributedManager {
            return if (!::instance.isInitialized) {
                instance = DistributedManager()
                instance
            } else instance
        }
    }
    //TODO сделать загрузку конкретных настроек, чтобы итзбавиться от публичного оверлода хранилищ
    private fun loadSettings(settingsBuilder: SettingsBuilder) {
        arrays.putAll(settingsBuilder.arrays)
        maps.putAll(settingsBuilder.maps)
    }
}