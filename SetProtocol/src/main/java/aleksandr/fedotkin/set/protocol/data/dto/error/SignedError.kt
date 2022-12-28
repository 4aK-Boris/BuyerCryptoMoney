package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class SignedError(val signature: String): DTO