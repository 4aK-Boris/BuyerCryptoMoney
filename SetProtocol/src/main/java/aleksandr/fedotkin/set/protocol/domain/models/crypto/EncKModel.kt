package aleksandr.fedotkin.set.protocol.domain.models.crypto

import aleksandr.fedotkin.set.protocol.core.Model

data class EncKModel(
    val signature: ByteArray,
    val data: ByteArray
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncKModel

        if (!signature.contentEquals(other.signature)) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}
