package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate

import kotlinx.serialization.Serializable

@Serializable
data class CardCInitRes(
    val ca: String,
    val cardCInitResTBS: CardCInitResTBS
)
