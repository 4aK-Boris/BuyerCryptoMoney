package aleksandr.fedotkin.set.protocol.data.dto.general

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class MessageID(
    val lIdC: String?,
    val lIdM: String?,
    val xId: String?
): DTO