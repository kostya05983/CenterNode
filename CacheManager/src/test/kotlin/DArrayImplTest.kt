import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DArrayImplTest {

    @Test
    fun addValue() {
        val dArrayIml = DArrayImpl<Int>()

        dArrayIml.add(2)
        assertEquals(1, dArrayIml.size)
        assertEquals(2, dArrayIml[0])
    }

    @Test
    fun testSize() {
        val dArrayImpl = DArrayImpl<Int>()
        dArrayImpl.add(2)
        assertEquals(1,dArrayImpl.size)
    }


}