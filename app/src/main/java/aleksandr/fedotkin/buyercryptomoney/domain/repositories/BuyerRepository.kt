package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyerModel

interface BuyerRepository {

    suspend fun getBuyers(): List<BuyerModel>
}
