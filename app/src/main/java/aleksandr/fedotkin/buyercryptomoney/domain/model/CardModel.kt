package aleksandr.fedotkin.buyercryptomoney.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CardModel(
    val number: String,
    val month: String,
    val year: String,
    val cvc: String
)
