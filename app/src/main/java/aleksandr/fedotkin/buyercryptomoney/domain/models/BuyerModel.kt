package aleksandr.fedotkin.buyercryptomoney.domain.models

data class BuyerModel(
    val id: Int,
    val nickName: String,
    val imageUrl: String,
    val amountOfMoney: Int
)