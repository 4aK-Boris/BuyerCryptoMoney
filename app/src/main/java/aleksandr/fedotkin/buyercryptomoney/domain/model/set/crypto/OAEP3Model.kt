package aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto

import javax.crypto.SecretKey

data class OAEP3Model<T>(
    val secretKey: SecretKey,
    val hash: ByteArray,
    val p: T
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OAEP3Model<*>

        if (secretKey != other.secretKey) return false
        if (!hash.contentEquals(other.hash)) return false
        if (p != other.p) return false

        return true
    }

    override fun hashCode(): Int {
        var result = secretKey.hashCode()
        result = 31 * result + hash.contentHashCode()
        result = 31 * result + (p?.hashCode() ?: 0)
        return result
    }
}
