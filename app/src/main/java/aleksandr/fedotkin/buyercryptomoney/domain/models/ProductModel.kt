package aleksandr.fedotkin.buyercryptomoney.domain.models

data class ProductModel(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val quantity: Int,
    val rating: Double,
    val price: Int,
    val sellerId: Int,
    val sellerTitle: String
)
