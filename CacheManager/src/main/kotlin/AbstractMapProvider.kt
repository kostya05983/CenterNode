abstract class AbstractMapProvider<K> {

    abstract fun loadAll()

    abstract fun load(key: K)

    abstract fun remove(key: K)

    abstract fun removeAll()
}