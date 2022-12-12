package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq

@kotlinx.serialization.Serializable
data class AcctData(
    val acctIdentification: String,
    val exNonce: String
)
