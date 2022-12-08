package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result

class BuyUseCase(
    private val buyRepository: BuyRepository
): BaseUseCase() {
    suspend fun buy(buyModel: BuyModel): Result<Unit> = safeCall {
        buyRepository.buy(buyModel = buyModel)
    }
}
