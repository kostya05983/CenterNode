interface MapProvider<K,V> {

    fun loadAll()

    fun load(key: K)

    fun add(key: K, value: V)

    //TODO what can we attach to this fun?
    fun addAll()

    fun remove(key: K)

    fun removeAll()
}