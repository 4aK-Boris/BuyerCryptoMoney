package aleksandr.fedotkin.buyercryptomoney.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val buyerId: Int,
    val sellerId: Int,
    val snippetId: Int
)
