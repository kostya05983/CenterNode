import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DArrayImplTest {

    @Test
    fun addValue() {
        val dArrayIml = DArrayImpl<Int>(NullListProvider())
        dArrayIml.add(2)
        assertEquals(1, dArrayIml.size)
        assertEquals(2, dArrayIml[0])
    }

    @Test
    fun size() {
        val dArrayImpl = DArrayImpl<Int>(NullListProvider())
        dArrayImpl.add(2)
        assertEquals(1, dArrayImpl.size)
    }

    @Test
    fun evict() {
        val dArrayImpl = DArrayImpl<Int>(NullListProvider())
        dArrayImpl.add(2)
        dArrayImpl.evict(value = 2)
        assertEquals(0, dArrayImpl.size)
    }

    @Test
    fun empty() {
        val dArrayImpl = DArrayImpl<Int>(NullListProvider())
        assertEquals(true, dArrayImpl.isEmpty())
    }

    @Nested
    inner class IteratorTest {
        @Test
        fun cycle() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            for (element in dArrayImpl)
                println(element)
        }

        @Test
        fun hasNextWhileEmpty() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            val iterator = dArrayImpl.iterator()
            assertEquals(false, iterator.hasNext())
        }

        @Test
        fun hasNext() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            val iterator = dArrayImpl.iterator()
            assertEquals(true, iterator.hasNext())
        }

        @Test
        fun cycleRemove() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            val iterator = dArrayImpl.iterator()
            while (iterator.hasNext()) {
                iterator.remove()
            }
            assertEquals(0, dArrayImpl.size)
        }
    }

    @Nested
    inner class IteratorListTest {
        @Test
        fun iteratorWithIndex() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            assertThrows<NoSuchElementException> { dArrayImpl.listIterator(3).hasNext() }
        }

        @Test
        fun hasNext() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            val listIterator = dArrayImpl.listIterator()
            assertEquals(false, listIterator.hasNext())
        }

        @Test
        fun hasNextTrue() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            val listIterator = dArrayImpl.listIterator()
            assertEquals(true, listIterator.hasNext())
        }

        @Test
        fun hasPrevious() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            var listIterator = dArrayImpl.listIterator()
            assertEquals(true, listIterator.hasNext())
            listIterator = dArrayImpl.listIterator()
            assertEquals(false, listIterator.hasPrevious())
        }

        @Test
        fun set() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            val listIterator = dArrayImpl.listIterator()
            listIterator.set(4)
            assertEquals(4, dArrayImpl[0])
        }

        @Test
        fun remove() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            val listIterator = dArrayImpl.listIterator()
            while (listIterator.hasNext()) {
                listIterator.remove()
            }
            assertEquals(0, dArrayImpl.size)
        }

        @Test
        fun nextIndex() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            val listIterator = dArrayImpl.listIterator()
            assertEquals(1, listIterator.nextIndex())
        }

        @Test
        fun previousIndex() {
            val dArrayImpl = DArrayImpl<Int>(NullListProvider())
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            dArrayImpl.add(2)
            val listIterator = dArrayImpl.listIterator()
            listIterator.next()
            listIterator.next()
            assertEquals(1, listIterator.previousIndex())
        }
    }
}