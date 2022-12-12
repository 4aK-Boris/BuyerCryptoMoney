package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

@kotlinx.serialization.Serializable
data class MerchantAcquirerID(
    val merchantBIN: String,
    val merchantID: String,
)
