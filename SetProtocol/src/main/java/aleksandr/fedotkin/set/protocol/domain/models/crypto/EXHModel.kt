package aleksandr.fedotkin.set.protocol.domain.models.crypto

import aleksandr.fedotkin.set.protocol.core.Model

data class EXHModel(
    val data: ByteArray,
    val secretKey: ByteArray
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EXHModel

        if (!data.contentEquals(other.data)) return false
        if (!secretKey.contentEquals(other.secretKey)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + secretKey.contentHashCode()
        return result
    }
}