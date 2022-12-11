package aleksandr.fedotkin.buyercryptomoney.data.dto.set.general

import kotlinx.serialization.Serializable

@Serializable
data class MessageWrapper<T>(
    val messageHeader: MessageHeader,
    val message: T,
    val mWExtension: String?
)