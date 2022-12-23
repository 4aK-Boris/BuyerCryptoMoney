package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyerRepository
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result

class BuyerUseCase(
    private val buyerRepository: BuyerRepository
): BaseUseCase() {

    suspend fun getBuyers(): Result<List<BuyerModel>> = safeCall {
        buyerRepository.getBuyers()
    }
}
