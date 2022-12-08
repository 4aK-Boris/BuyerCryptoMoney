package aleksandr.fedotkin.buyercryptomoney.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CardDTO(
    val number: String,
    val month: Int,
    val year: Int,
    val cvc: Int
)
