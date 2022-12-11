package aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto

@kotlinx.serialization.Serializable
data class OAEP3(
    val key: String,
    val hash: String,
    val p: String
)
