package aleksandr.fedotkin.buyercryptomoney.data.mappers

import aleksandr.fedotkin.buyercryptomoney.data.dto.PurchaseDTO
import aleksandr.fedotkin.buyercryptomoney.domain.models.PurchaseModel

class PurchaseMapper {

    fun map(purchaseModel: PurchaseModel): PurchaseDTO {
        return PurchaseDTO(
            buyerId = purchaseModel.buyerId,
            sellerId = purchaseModel.sellerId,
            productId = purchaseModel.productId,
            count = purchaseModel.count
        )
    }

}
