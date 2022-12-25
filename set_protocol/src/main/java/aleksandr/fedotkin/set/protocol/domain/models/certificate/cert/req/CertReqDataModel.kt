package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger
import java.time.LocalDateTime

data class CertReqDataModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE3: BigInteger,
    val lidCA: BigInteger?,
    val challCA: BigInteger?,
    val requestType: RequestType,
    val requestDate: LocalDateTime,
    val idData: IDDataModel?,
    val regFormID: BigInteger,
    val regForm: List<RegFormItemsModel>,
    val caBackKeyData: CABackKeyDataModel?,
    val publicKeySorE: PublicKeySorEModel,
    val eeThumb: ByteArray,
    val thumbs: List<ByteArray>
) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CertReqDataModel

        if (rrpID != other.rrpID) return false
        if (lidEE != other.lidEE) return false
        if (challEE3 != other.challEE3) return false
        if (lidCA != other.lidCA) return false
        if (challCA != other.challCA) return false
        if (requestType != other.requestType) return false
        if (requestDate != other.requestDate) return false
        if (idData != other.idData) return false
        if (regFormID != other.regFormID) return false
        if (regForm != other.regForm) return false
        if (caBackKeyData != other.caBackKeyData) return false
        if (publicKeySorE != other.publicKeySorE) return false
        if (!eeThumb.contentEquals(other.eeThumb)) return false
        if (thumbs != other.thumbs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rrpID.hashCode()
        result = 31 * result + lidEE.hashCode()
        result = 31 * result + challEE3.hashCode()
        result = 31 * result + (lidCA?.hashCode() ?: 0)
        result = 31 * result + (challCA?.hashCode() ?: 0)
        result = 31 * result + requestType.hashCode()
        result = 31 * result + requestDate.hashCode()
        result = 31 * result + (idData?.hashCode() ?: 0)
        result = 31 * result + regFormID.hashCode()
        result = 31 * result + regForm.hashCode()
        result = 31 * result + (caBackKeyData?.hashCode() ?: 0)
        result = 31 * result + publicKeySorE.hashCode()
        result = 31 * result + eeThumb.contentHashCode()
        result = 31 * result + thumbs.hashCode()
        return result
    }
}
