import java.util.*

class DArrayImpl<V> private constructor(
        private var array: Array<V>,
        override val listProvider: ListProvider<V>
) : DArray<V> {
    private val defaultOffSet = 2
    private val defaultResizeAddition = 10
    private var _size = 0

    override val size: Int
        get() = _size

    constructor(listProvider: ListProvider<V>) : this(10, listProvider)

    constructor(initialSize: Int, listProvider: ListProvider<V>) : this(arrayOfUninitializedElements(initialSize), listProvider)

    constructor(collection: DArray<V>) : this(collection.size, collection.listProvider) {
        addAll(collection)
    }

    private fun resizeIfNeed() {
        if ((array.size - size) <= defaultOffSet)
            resizeArray()
    }

    @Suppress("UNCHECKED_CAST")
    private fun resizeArray() {
        resizeIfNeed()
        array = array.copyOf(array.size + defaultResizeAddition) as Array<V>
    }

    override fun contains(element: V): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(index: Int): V {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun indexOf(element: V): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lastIndexOf(element: V): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(element: V): Boolean {
        resizeIfNeed()
        array[size]
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(index: Int, element: V) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(index: Int, elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(collection: DArray<V>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): MutableIterator<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(): MutableListIterator<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(index: Int): MutableListIterator<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(element: V): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAt(index: Int): V {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(index: Int, element: V): V {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}