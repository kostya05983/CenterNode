/**
 * Returns an array of objects of the given type with the given [size] with _uninitialized_ values.
 * Attempts to read _uninitialized_ values from this array work in implementation-dependent manner,
 * either throwing exception or returning some value.
 */
@Suppress("UNCHECKED_CAST")
fun <E> arrayOfUninitializedElements(size: Int): Array<E> {
    return (if (size == 0) emptyArray<Any?>() else arrayOfNulls<Any>(size)) as Array<E>
}