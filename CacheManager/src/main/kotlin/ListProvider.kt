interface ListProvider<V> {
    fun load(size: Int)

    fun loadAll()

    fun save(value: V)

    fun saveAll()
}