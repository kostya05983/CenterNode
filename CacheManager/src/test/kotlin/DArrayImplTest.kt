import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
}