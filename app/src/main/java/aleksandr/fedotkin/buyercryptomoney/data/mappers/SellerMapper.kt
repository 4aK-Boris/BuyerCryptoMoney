package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO
import aleksandr.fedotkin.buyercryptomoney.domain.models.SellerModel

class SellerMapper {

    fun map(sellerDTO: SellerDTO): SellerModel {
        return SellerModel(title = sellerDTO.title)
    }

}