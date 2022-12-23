package aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class CardCInitRes(
    val ca: String,
    val cardCInitResTBS: CardCInitResTBS
): DTO
