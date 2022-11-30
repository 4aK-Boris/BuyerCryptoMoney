package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.ProductModel

class ProductMapper {

    fun map(productModel: ProductModel): ProductDTO {
        return ProductDTO(
            buyerId = productModel.buyerId,
            sellerId = productModel.sellerId,
            snippetId = productModel.snippetId
        )
    }
}