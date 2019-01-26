//additional fields for map
interface DArray<V> : MutableList<V> {
    val listProvider: ListProvider<V>

    fun evict(index: Int)

    fun evict(value: V):Boolean

    fun evictAll()

}