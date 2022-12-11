package aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto

@kotlinx.serialization.Serializable
data class CryptoData(
    val cipherData: String,
    val oaep: String
)
