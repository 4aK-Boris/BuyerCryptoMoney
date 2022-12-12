package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

@kotlinx.serialization.Serializable
data class AcquirerID(
    val acquirerBIN: String,
    val acquirerBusinessID: String?
)
