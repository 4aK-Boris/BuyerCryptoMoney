package aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto

@kotlinx.serialization.Serializable
data class OAEP3(
    val secretKey: String,
    val hash: String,
    val p: String
)
