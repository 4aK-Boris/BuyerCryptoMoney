package aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.Model
import javax.crypto.SecretKey

data class OAEP4Model<T: Model>(
    val secretKey: SecretKey,
    val hash: ByteArray,
    val data: T
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OAEP4Model<*>

        if (secretKey != other.secretKey) return false
        if (!hash.contentEquals(other.hash)) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = secretKey.hashCode()
        result = 31 * result + hash.contentHashCode()
        result = 31 * result + data.hashCode()
        return result
    }

}
