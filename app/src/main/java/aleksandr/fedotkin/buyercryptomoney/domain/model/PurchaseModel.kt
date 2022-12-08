package aleksandr.fedotkin.buyercryptomoney.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseModel(
    val buyerId: Int,
    val sellerId: Int,
    val productId: Int,
    val count: Int
)
