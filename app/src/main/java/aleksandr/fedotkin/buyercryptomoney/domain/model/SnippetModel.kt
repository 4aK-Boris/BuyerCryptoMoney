package aleksandr.fedotkin.buyercryptomoney.domain.model

data class SnippetModel(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val quantity: Int,
    val rating: Double,
    val price: Int,
    val sellerId: Int
)
