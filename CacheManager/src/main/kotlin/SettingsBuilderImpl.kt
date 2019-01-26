import kotlin.reflect.KClass

class SettingsBuilderImpl: SettingsBuilder {

    override fun addSource(clazz: KClass<*>) {
        when (clazz) {
            is DMap<*,*> -> {}
            is DArray<*> -> {}
        }
    }
}