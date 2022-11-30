package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.UpdateBuyerDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.UpdateBuyerModel

class BuyerMapper {

    fun map(buyerDTO: BuyerDTO): BuyerModel {
        return BuyerModel(
            id = buyerDTO.id,
            nickName = buyerDTO.nickName,
            amountOfMoney = buyerDTO.amountOfMoney,
            imageUrl = buyerDTO.imageUrl
        )
    }

    fun map(updateBuyerDTO: UpdateBuyerDTO): UpdateBuyerModel {
        return UpdateBuyerModel(
            id = updateBuyerDTO.id,
            amountOfMoney = updateBuyerDTO.amountOfMoney
        )
    }
}