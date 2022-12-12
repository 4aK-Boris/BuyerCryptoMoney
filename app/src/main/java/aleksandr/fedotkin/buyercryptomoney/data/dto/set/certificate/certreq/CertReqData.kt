package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RequestType
import kotlinx.datetime.LocalDateTime

@kotlinx.serialization.Serializable
data class CertReqData(
    val rrpID: String,
    val lidEE: String,
    val challEE3: String,
    val lidCA: String?,
    val challCA: String?,
    val requestType: RequestType,
    val requestDate: LocalDateTime,
    val idData: IDData?,
    val regFormID: String,
    val regForm: List<RegFormItems>,
    val caBackKeyData: CABackKeyData?,
    val publicKeySorE: PublicKeySorE,
    val eeThumb: String,
    val thumbs: List<String>
)
