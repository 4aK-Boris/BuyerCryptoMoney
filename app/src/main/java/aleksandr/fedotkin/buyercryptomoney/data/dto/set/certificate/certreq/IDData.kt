package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

@kotlinx.serialization.Serializable
data class IDData(
    val merchantAcquirerID: MerchantAcquirerID,
    val acquirerID: AcquirerID
)
