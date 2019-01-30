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

    /**
     * throw IndexOfOutBoundsException
     * if element's index isn't in range
     */
    private fun checkIndex(index: Int) {
        if (index < 0 || index >= _size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private fun outOfBoundsMsg(index: Int): String {
        return "Index: $index, Size: $_size"
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

    /**
     * return value at specified index if it is in cache
     */
    override fun get(index: Int): V {
        checkIndex(index)
        return array[index]
    }

    override fun indexOf(element: V): Int {
        for (i in 0 until _size) {
            if (array[i] == element)
                return i
        }
        return -1
    }

    override fun isEmpty(): Boolean {
        return _size == 0
    }

    override fun lastIndexOf(element: V): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(element: V): Boolean {
        resizeIfNeed()
        array[_size++] = element
        listProvider.add(element)
        return true
    }

    override fun add(index: Int, element: V) {
        checkIndex(index)
        resizeIfNeed()
        array[_size++] = element
    }

    override fun addAll(index: Int, elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<V>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        array = arrayOfUninitializedElements(10)
        _size = 0
    }

    override fun iterator(): MutableIterator<V> {
        return IteratorArr()
    }

    override fun listIterator(): MutableListIterator<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(index: Int): MutableListIterator<V> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //TODO thread safety
    private inner class IteratorArr : MutableIterator<V> {
        private var currentIndex = 0

        override fun hasNext(): Boolean {
            return currentIndex != size
        }

        override fun next(): V {
            if (currentIndex >= size)
                throw NoSuchElementException()
            currentIndex++
            return array[currentIndex - 1]
        }

        override fun remove() {
            if (currentIndex >= size)
                throw NoSuchElementException()
            this@DArrayImpl.remove(array[currentIndex])
            currentIndex--
        }
    }

    //TODo make if element is the first init with default size and emptyArray
    private fun moveElements(index: Int) {
        val newArray = arrayOfUninitializedElements<V>(_size - 1)
        var newIndex = 0
        if (_size != 1)
            for (i in 0 until _size) {
                if (i != index) {
                    newArray[newIndex] = array[i]
                    newIndex++
                }
            }
        array = newArray
    }

    override fun evict(index: Int) {
        checkIndex(index)
        moveElements(_size)
        _size--
    }

    /**
     * remove value from cache
     */
    override fun evict(value: V): Boolean {
        val index = indexOf(value)
        return if (index != -1) {
            moveElements(_size)
            _size--
            true
        } else false
    }

    /**
     * remove all values from cache
     */
    override fun evictAll() {
        array = arrayOfUninitializedElements(10)
        _size = 0
    }

    /**
     * remove value from cache and db
     */
    //TODO необходимо сделать транзакцию,чтобы можно было откатывать изменения,
    override fun remove(element: V): Boolean {
        evict(element)
        listProvider.remove(element)
        return true
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