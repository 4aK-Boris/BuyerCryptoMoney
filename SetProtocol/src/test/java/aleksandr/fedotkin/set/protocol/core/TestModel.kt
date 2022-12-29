package aleksandr.fedotkin.set.protocol.core

import java.math.BigInteger

data class TestModel(
    val number: BigInteger,
    val array: ByteArray
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestModel

        if (number != other.number) return false
        if (!array.contentEquals(other.array)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number.hashCode()
        result = 31 * result + array.contentHashCode()
        return result
    }
}