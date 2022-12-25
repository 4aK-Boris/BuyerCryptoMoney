package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class SignedErrorModel(val signature: ByteArray): Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignedErrorModel

        if (!signature.contentEquals(other.signature)) return false

        return true
    }

    override fun hashCode(): Int {
        return signature.contentHashCode()
    }
}