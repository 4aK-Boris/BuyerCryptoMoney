package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.models.ProductModel

interface ProductRepository {

    suspend fun getProducts(): List<ProductModel>
}
