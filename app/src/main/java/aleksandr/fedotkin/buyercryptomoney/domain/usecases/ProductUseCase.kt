package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.ProductRepository

class ProductUseCase(
    private val productRepository: ProductRepository
): BaseUseCase() {

    suspend fun getProducts(): Result<List<ProductModel>> = safeCall {
        productRepository.getProducts()
    }

    suspend fun getProduct(productId: Int): Result<ProductModel> = safeCall {
        productRepository.getProduct(productId = productId)
    }
}
