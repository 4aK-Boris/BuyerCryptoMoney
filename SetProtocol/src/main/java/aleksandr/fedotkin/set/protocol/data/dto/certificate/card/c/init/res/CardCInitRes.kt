package aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class CardCInitRes(
    val signature: String,
    val cardCInitResTBS: CardCInitResTBS
): DTO
