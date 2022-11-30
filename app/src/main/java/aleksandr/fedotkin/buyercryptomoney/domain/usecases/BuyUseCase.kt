package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.model.PurchaseModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.UpdateBuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository

class BuyUseCase(
    private val buyRepository: BuyRepository
) {
    suspend fun buy(purchaseModel: PurchaseModel): UpdateBuyerModel {
        return buyRepository.buy(purchaseModel = purchaseModel)
    }
}