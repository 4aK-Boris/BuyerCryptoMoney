package aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class CardCInitResTBS(
    val rrpID: String,
    val lidEE: String,
    val challEE: String,
    val lidCA: String,
    val caeThumb: String,
    val brandCRLIdentifier: List<String>,
    val thumbs: List<String>
): DTO
