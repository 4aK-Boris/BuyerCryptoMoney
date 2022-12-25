package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageHeader
import kotlinx.serialization.Serializable

@Serializable
data class ErrorMsg<T>(
    val messageHeader: MessageHeader,
    val badWrapper: T
): DTO
