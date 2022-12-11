package aleksandr.fedotkin.buyercryptomoney.data.dto.set.error

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageHeader
import kotlinx.serialization.Serializable

@Serializable
data class ErrorMsg<T>(
    val messageHeader: MessageHeader,
    val badWrapper: T
)
