//additional fields for map
interface DArray<V> : MutableList<V> {
    val listProvider: ListProvider<V>

    override fun add(element: V): Boolean

    fun addAll(collection: DArray<V>)
}