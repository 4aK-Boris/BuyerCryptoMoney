package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res

import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncKModel

data class CertResModel(
    val signature: ByteArray,
    val encK: EncKModel
): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CertResModel

        if (!signature.contentEquals(other.signature)) return false
        if (encK != other.encK) return false

        return true
    }

    override fun hashCode(): Int {
        var result = signature.contentHashCode()
        result = 31 * result + encK.hashCode()
        return result
    }
}
