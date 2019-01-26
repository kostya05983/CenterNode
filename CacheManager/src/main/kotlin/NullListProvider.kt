class NullListProvider<V>: ListProvider<V> {
    override fun load(size: Int) {
    }

    override fun loadAll() {
    }

    override fun add(value: V) {
    }

    override fun addAll(vararg value: V) {
    }

    override fun remove(value: V) {
    }

    override fun removeAll() {
    }
}