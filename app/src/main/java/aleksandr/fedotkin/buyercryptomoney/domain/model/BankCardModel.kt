package aleksandr.fedotkin.buyercryptomoney.domain.model

data class BankCardModel(
    val number: String,
    val month: String,
    val year: String,
    val cvc: String
)
