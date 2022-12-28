package aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.contentEquals
import java.math.BigInteger

data class CardCInitReqModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE: BigInteger,
    val brandID: BigInteger,
    val thumbs: List<ByteArray>
) : Model {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardCInitReqModel

        if (rrpID != other.rrpID) return false
        if (lidEE != other.lidEE) return false
        if (challEE != other.challEE) return false
        if (brandID != other.brandID) return false
        if (!thumbs.contentEquals(other.thumbs)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rrpID.hashCode()
        result = 31 * result + lidEE.hashCode()
        result = 31 * result + challEE.hashCode()
        result = 31 * result + brandID.hashCode()
        result = 31 * result + thumbs.hashCode()
        return result
    }
}
