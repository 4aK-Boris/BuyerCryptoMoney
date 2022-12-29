package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyModel

interface BuyRepository {
    suspend fun buy(buyModel: BuyModel)

    suspend fun getCode()

    suspend fun checkCode(code: Int): Boolean
}
