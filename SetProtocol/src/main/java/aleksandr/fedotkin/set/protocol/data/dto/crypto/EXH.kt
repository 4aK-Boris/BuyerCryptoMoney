package aleksandr.fedotkin.set.protocol.data.dto.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class EXH(
    val data: String,
    val secretKey: String
): DTO