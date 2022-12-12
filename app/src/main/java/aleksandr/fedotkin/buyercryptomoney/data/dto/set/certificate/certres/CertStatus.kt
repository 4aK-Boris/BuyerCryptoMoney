package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres

data class CertStatus(
    val certStatusCode: CertStatusCode,
    val nonceCCA: String?,
    val eeMessage: String,
    val caMsg: CAMsg,
    val failedItemSeq: List<FailedItem>
)
