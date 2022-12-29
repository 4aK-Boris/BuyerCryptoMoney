package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyDTO
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel

class BuyMapper(
    private val purchaseMapper: PurchaseMapper,
    private val cardMapper: CardMapper
) {

    fun map(buyModel: BuyModel): BuyDTO {
        return BuyDTO(
            purchase = purchaseMapper.map(purchaseModel = buyModel.purchase),
            card = cardMapper.map(cardModel = buyModel.card)
        )
    }
 }
