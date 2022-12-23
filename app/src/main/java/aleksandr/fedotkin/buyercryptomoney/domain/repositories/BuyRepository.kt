package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyModel

interface BuyRepository {
    suspend fun buy(buyModel: BuyModel)
}
