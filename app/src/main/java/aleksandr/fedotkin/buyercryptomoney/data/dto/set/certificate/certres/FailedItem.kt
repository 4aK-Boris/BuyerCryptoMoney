package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres

@kotlinx.serialization.Serializable
data class FailedItem(
    val itemNumber: Int,
    val itemReason: String
)
