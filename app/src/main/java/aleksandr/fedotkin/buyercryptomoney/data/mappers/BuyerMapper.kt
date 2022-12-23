package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyerModel

class BuyerMapper {

    fun map(buyerDTO: BuyerDTO): BuyerModel {
        return BuyerModel(
            id = buyerDTO.id,
            nickName = buyerDTO.nickName,
            amountOfMoney = buyerDTO.amountOfMoney,
            imageUrl = buyerDTO.imageUrl
        )
    }
}
