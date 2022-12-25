package aleksandr.fedotkin.set.protocol.domain.models.crypto

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class EncXModel(
    val signature: ByteArray,
    val data: ByteArray,
    val secretKeyAndData: ByteArray
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncXModel

        if (!signature.contentEquals(other.signature)) return false
        if (!data.contentEquals(other.data)) return false
        if (!secretKeyAndData.contentEquals(other.secretKeyAndData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + secretKeyAndData.contentHashCode()
        return result
    }
}
