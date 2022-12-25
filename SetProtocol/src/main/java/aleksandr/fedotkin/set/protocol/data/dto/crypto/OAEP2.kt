package aleksandr.fedotkin.set.protocol.data.dto.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class OAEP2(
    val secretKey: String,
    val p: String
): DTO