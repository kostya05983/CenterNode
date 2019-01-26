import kotlin.reflect.KClass

interface SettingsBuilder {
    fun addSource(clazz: KClass<*>)
}