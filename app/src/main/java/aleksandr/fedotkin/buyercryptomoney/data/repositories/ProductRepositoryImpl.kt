package aleksandr.fedotkin.buyercryptomoney.data.repositories

import aleksandr.fedotkin.buyercryptomoney.data.mappers.ProductMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.models.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.ProductRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.SellerRepository

class ProductRepositoryImpl(
    private val productMapper: ProductMapper,
    private val sellerRepository: SellerRepository,
    private val networkAPI: NetworkAPI
) : ProductRepository {

    override suspend fun getProducts(): List<ProductModel> {
        return networkAPI.getSnippets().map { productDTO ->
            productMapper.map(
                productDTO = productDTO,
                sellerRepository.getSeller(sellerId = productDTO.sellerId)
            )
        }
    }
}
