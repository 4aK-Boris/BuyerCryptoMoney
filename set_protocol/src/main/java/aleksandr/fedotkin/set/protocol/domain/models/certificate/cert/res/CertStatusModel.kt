package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CertStatusCode
import aleksandr.fedotkin.set.protocol.domain.models.Model
import javax.crypto.SecretKey

data class CertStatusModel(
    val certStatusCode: CertStatusCode,
    val nonceCCA: SecretKey?,
    val eeMessage: String,
    val caMsg: CAMsgModel,
    val failedItemSeq: List<FailedItemModel>
) : Model
