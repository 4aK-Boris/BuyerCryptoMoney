package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO
import aleksandr.fedotkin.buyercryptomoney.domain.models.ProductModel
import aleksandr.fedotkin.buyercryptomoney.domain.models.SellerModel

class ProductMapper {

    fun map(productDTO: ProductDTO, sellerModel: SellerModel): ProductModel {
        return ProductModel(
            id = productDTO.id,
            sellerId = productDTO.sellerId,
            quantity = productDTO.quantity,
            imageUrl = productDTO.imageUrl,
            title = productDTO.title,
            rating = productDTO.rating,
            price = productDTO.price,
            sellerTitle = sellerModel.title
        )
    }
}
