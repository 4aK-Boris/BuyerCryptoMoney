package aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto

data class CryptoDataModel(
    val cipherData: ByteArray,
    val oaep: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CryptoDataModel

        if (!cipherData.contentEquals(other.cipherData)) return false
        if (!oaep.contentEquals(other.oaep)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cipherData.contentHashCode()
        result = 31 * result + oaep.contentHashCode()
        return result
    }
}
