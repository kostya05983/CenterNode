interface ListProvider<V> {
    fun load(size: Int)

    fun loadAll()

    fun add(value: V)

    fun addAll(vararg value: V)

    fun remove(value: V)

    fun removeAll()
}