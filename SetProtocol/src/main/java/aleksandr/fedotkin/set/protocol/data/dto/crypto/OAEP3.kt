package aleksandr.fedotkin.set.protocol.data.dto.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class OAEP3(
    val secretKey: String,
    val hash: String,
    val p: String
): DTO
