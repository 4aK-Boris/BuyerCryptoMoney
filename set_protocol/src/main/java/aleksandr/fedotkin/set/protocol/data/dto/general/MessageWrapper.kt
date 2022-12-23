package aleksandr.fedotkin.set.protocol.data.dto.general

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class MessageWrapper<T>(
    val messageHeader: MessageHeader,
    val message: T,
    val mWExtension: String?
): DTO