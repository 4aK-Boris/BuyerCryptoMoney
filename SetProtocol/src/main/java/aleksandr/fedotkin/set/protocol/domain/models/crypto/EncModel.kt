package aleksandr.fedotkin.set.protocol.domain.models.crypto

data class EncModel(
    val signature: ByteArray,
    val secretKey: ByteArray,
    val data: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncModel

        if (!signature.contentEquals(other.signature)) return false
        if (!secretKey.contentEquals(other.secretKey)) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + secretKey.contentHashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}
