package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageHeader
import kotlinx.serialization.Serializable

@Serializable
data class ErrorMsg<T: DTO>(
    val messageHeader: MessageHeader,
    val badWrapper: T
): DTO
