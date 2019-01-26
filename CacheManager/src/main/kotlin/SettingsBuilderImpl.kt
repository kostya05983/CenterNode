import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class SettingsBuilderImpl : SettingsBuilder {
    override val maps: ConcurrentHashMap<String, DMap<*, *>> = ConcurrentHashMap()
    override val arrays: ConcurrentHashMap<String, DArray<*>> = ConcurrentHashMap()

    //TODO ограничения на тип wildCast
    override fun addSource(key: String, clazz: KClass<*>) {
        when (clazz) {
            is DMap<*, *> -> {
                maps[key] = clazz.primaryConstructor?.call() as DMap<*, *>
            }
            is DArray<*> -> {
                arrays[key] = clazz.primaryConstructor?.call() as DArray<*>
            }
        }
    }
}