package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres.CertStatusCode
import javax.crypto.SecretKey

data class CertStatusModel(
    val certStatusCode: CertStatusCode,
    val nonceCCA: SecretKey?,
    val eeMessage: String,
    val caMsg: CAMsgModel,
    val failedItemSeq: List<FailedItemModel>
)
