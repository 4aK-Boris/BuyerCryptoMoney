package aleksandr.fedotkin.buyercryptomoney.domain.model

data class CardModel(
    val number: String,
    val month: String,
    val year: String,
    val cvc: String
)
