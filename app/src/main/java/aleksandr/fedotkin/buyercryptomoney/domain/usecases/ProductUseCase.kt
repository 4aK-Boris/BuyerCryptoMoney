package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result
import aleksandr.fedotkin.buyercryptomoney.domain.models.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.ProductRepository

class ProductUseCase(
    private val productRepository: ProductRepository
): BaseUseCase() {

    suspend fun getProducts(): Result<List<ProductModel>> = safeCall {
        productRepository.getProducts()
    }
}
