package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.models.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.ProductRepository
import aleksandr.fedotkin.core.BaseUseCase
import aleksandr.fedotkin.core.Result

class ProductUseCase(
    private val productRepository: ProductRepository
): BaseUseCase() {

    suspend fun getProducts(): Result<List<ProductModel>> = safeCall {
        productRepository.getProducts()
    }
}
