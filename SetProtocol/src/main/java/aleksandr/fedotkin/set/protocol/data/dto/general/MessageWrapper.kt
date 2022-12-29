package aleksandr.fedotkin.set.protocol.data.dto.general

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class MessageWrapper<T: DTO>(
    val messageHeader: MessageHeader,
    val message: T,
    val mWExtension: String?
): DTO