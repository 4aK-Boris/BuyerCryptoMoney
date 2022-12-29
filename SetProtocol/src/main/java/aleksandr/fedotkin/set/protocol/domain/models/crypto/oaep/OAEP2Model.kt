package aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.Model
import javax.crypto.SecretKey

data class OAEP2Model(val secretKey: SecretKey, val hash: ByteArray): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OAEP2Model

        if (secretKey != other.secretKey) return false
        if (!hash.contentEquals(other.hash)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = secretKey.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}