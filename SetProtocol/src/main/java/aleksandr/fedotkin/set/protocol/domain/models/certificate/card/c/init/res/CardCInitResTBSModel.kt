package aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.contentEquals
import java.math.BigInteger

data class CardCInitResTBSModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE: BigInteger,
    val lidCA: BigInteger,
    val caeThumb: ByteArray,
    val brandCRLIdentifier: List<ByteArray>,
    val thumbs: List<ByteArray>
) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardCInitResTBSModel

        if (rrpID != other.rrpID) return false
        if (lidEE != other.lidEE) return false
        if (challEE != other.challEE) return false
        if (lidCA != other.lidCA) return false
        if (!caeThumb.contentEquals(other.caeThumb)) return false
        if (!brandCRLIdentifier.contentEquals(other.brandCRLIdentifier)) return false
        if (!thumbs.contentEquals(other.thumbs)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rrpID.hashCode()
        result = 31 * result + lidEE.hashCode()
        result = 31 * result + challEE.hashCode()
        result = 31 * result + lidCA.hashCode()
        result = 31 * result + caeThumb.contentHashCode()
        result = 31 * result + brandCRLIdentifier.hashCode()
        result = 31 * result + thumbs.hashCode()
        return result
    }
}
