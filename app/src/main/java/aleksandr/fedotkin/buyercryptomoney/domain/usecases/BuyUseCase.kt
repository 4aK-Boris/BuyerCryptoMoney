package aleksandr.fedotkin.buyercryptomoney.domain.usecases

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.common.InvalidCode
import aleksandr.fedotkin.buyercryptomoney.domain.common.Result
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository

class BuyUseCase(
    private val buyRepository: BuyRepository
): BaseUseCase() {
    suspend fun buy(buyModel: BuyModel): Result<Unit> = safeCall {
        buyRepository.buy(buyModel = buyModel)
    }

    suspend fun getCode(): Result<Unit> = safeCall {
        buyRepository.getCode()
    }

    suspend fun checkCode(code: Int): Result<Unit> = safeCall {
        if (!buyRepository.checkCode(code = code)) {
            throw InvalidCode()
        }
    }
}
