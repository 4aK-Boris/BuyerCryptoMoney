package aleksandr.fedotkin.buyercryptomoney.data.dto.set.general

import kotlinx.serialization.Serializable

@Serializable
data class MessageID(
    val lIdC: String?,
    val lIdM: String?,
    val xId: String?
)