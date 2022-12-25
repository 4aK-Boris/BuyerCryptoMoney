package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository
import aleksandr.fedotkin.core.BaseUseCase
import aleksandr.fedotkin.core.Result

class BuyUseCase(
    private val buyRepository: BuyRepository
): BaseUseCase() {
    suspend fun buy(buyModel: BuyModel): Result<Unit> = safeCall {
        buyRepository.buy(buyModel = buyModel)
    }
}
