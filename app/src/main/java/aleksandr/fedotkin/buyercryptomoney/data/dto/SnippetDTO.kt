package aleksandr.fedotkin.buyercryptomoney.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SnippetDTO(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val quantity: Int,
    val rating: Double,
    val price: Int,
    val sellerId: Int
)
