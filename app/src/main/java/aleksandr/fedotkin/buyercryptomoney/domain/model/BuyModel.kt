package aleksandr.fedotkin.buyercryptomoney.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BuyModel(
    val purchase: PurchaseModel,
    val card: CardModel
)
