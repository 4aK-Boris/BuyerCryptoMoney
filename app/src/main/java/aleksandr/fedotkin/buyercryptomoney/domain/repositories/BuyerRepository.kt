package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel

interface BuyerRepository {

    suspend fun getBuyers(): List<BuyerModel>
}
