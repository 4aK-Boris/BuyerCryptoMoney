package aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class OAEP2(val secretKey: String, val hash: String): DTO