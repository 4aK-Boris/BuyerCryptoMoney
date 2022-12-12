package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres

@kotlinx.serialization.Serializable
data class CertStatus(
    val certStatusCode: CertStatusCode,
    val nonceCCA: String?,
    val eeMessage: String,
    val caMsg: CAMsg,
    val failedItemSeq: List<FailedItem>
)
