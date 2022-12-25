package aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class CardCInitReq(
    val rrpID: String,
    val lidEE: String,
    val challEE: String,
    val brandID: String,
    val thumbs: List<String>
): DTO
