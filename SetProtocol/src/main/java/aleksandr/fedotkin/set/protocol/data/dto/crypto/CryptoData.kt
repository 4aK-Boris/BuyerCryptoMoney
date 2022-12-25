package aleksandr.fedotkin.set.protocol.data.dto.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class CryptoData(
    val cipherData: String,
    val oaep: String
): DTO
