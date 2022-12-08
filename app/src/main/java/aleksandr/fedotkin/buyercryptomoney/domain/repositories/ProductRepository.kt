package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel

interface ProductRepository {

    suspend fun getProducts(): List<ProductModel>
}
