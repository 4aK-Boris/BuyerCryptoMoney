package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

@kotlinx.serialization.Serializable
data class CABackKeyData(
    val caaIgId: String,
    val caKey: String
)
