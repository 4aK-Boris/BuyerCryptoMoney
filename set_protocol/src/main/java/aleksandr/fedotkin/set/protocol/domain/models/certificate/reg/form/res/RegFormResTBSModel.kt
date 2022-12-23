package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.RequestType
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger

data class RegFormResTBSModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE2: BigInteger,
    val lidCA: BigInteger,
    val challCA: BigInteger,
    val caeThumb: ByteArray,
    val requestType: RequestType,
    val regFormOrReferral: RegFormOrReferralModel,
    val brandCRLIdentifier: List<ByteArray>,
    val thumbs: List<ByteArray>
) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegFormResTBSModel

        if (rrpID != other.rrpID) return false
        if (lidEE != other.lidEE) return false
        if (challEE2 != other.challEE2) return false
        if (lidCA != other.lidCA) return false
        if (challCA != other.challCA) return false
        if (!caeThumb.contentEquals(other.caeThumb)) return false
        if (requestType != other.requestType) return false
        if (regFormOrReferral != other.regFormOrReferral) return false
        if (brandCRLIdentifier != other.brandCRLIdentifier) return false
        if (thumbs != other.thumbs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rrpID.hashCode()
        result = 31 * result + lidEE.hashCode()
        result = 31 * result + challEE2.hashCode()
        result = 31 * result + lidCA.hashCode()
        result = 31 * result + challCA.hashCode()
        result = 31 * result + caeThumb.contentHashCode()
        result = 31 * result + requestType.hashCode()
        result = 31 * result + regFormOrReferral.hashCode()
        result = 31 * result + brandCRLIdentifier.hashCode()
        result = 31 * result + thumbs.hashCode()
        return result
    }
}
