package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyerRepository

class BuyerUseCase(
    private val buyerRepository: BuyerRepository
): BaseUseCase() {

    suspend fun getBuyers(): Result<List<BuyerModel>> = safeCall {
        buyerRepository.getBuyers()
    }
}
