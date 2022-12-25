package aleksandr.fedotkin.set.protocol.data.dto.general

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class MessageHeader(
    val version: String,
    val revision: String,
    val messageID: MessageID?,
    val rrpId: String?,
    val sWIdent: String?,
    val date: LocalDateTime
): DTO
