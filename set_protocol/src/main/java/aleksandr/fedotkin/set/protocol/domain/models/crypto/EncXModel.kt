package aleksandr.fedotkin.set.protocol.domain.models.crypto

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class EncXModel(
    val ee: ByteArray,
    val ca: ByteArray
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncXModel

        if (!ee.contentEquals(other.ee)) return false
        if (!ca.contentEquals(other.ca)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ee.contentHashCode()
        result = 31 * result + ca.contentHashCode()
        return result
    }
}
