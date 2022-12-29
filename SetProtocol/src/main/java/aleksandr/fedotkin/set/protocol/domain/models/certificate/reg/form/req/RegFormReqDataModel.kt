package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.contentEquals
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import java.math.BigInteger

data class RegFormReqDataModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE2: BigInteger,
    val lidCA: BigInteger,
    val requestType: RequestType,
    val language: Language,
    val thumbs: List<ByteArray>
) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegFormReqDataModel

        if (rrpID != other.rrpID) return false
        if (lidEE != other.lidEE) return false
        if (challEE2 != other.challEE2) return false
        if (lidCA != other.lidCA) return false
        if (requestType != other.requestType) return false
        if (language != other.language) return false
        if (!thumbs.contentEquals(other.thumbs)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rrpID.hashCode()
        result = 31 * result + lidEE.hashCode()
        result = 31 * result + challEE2.hashCode()
        result = 31 * result + lidCA.hashCode()
        result = 31 * result + requestType.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + thumbs.hashCode()
        return result
    }
}
