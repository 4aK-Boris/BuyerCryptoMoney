package aleksandr.fedotkin.set.protocol.data.dto.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class EncK(
    val signature: String,
    val data: String,
): DTO
