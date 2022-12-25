package aleksandr.fedotkin.set.protocol.data.dto.crypto

import kotlinx.serialization.Serializable

@Serializable
data class EncX(
    val signature: String,
    val data: String,
    val secretKeyAndData: String
)
