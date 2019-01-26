import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

interface SettingsBuilder {
    val maps: ConcurrentHashMap<String, DMap<*, *>>
    val arrays: ConcurrentHashMap<String, DArray<*>>
    fun addSource(key: String, clazz: KClass<*>)
}