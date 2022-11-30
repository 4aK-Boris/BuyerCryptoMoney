package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.model.PurchaseModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.UpdateBuyerModel

interface BuyRepository {
    suspend fun buy(purchaseModel: PurchaseModel): UpdateBuyerModel
}