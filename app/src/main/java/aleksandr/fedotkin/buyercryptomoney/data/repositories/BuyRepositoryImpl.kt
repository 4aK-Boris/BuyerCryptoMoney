package aleksandr.fedotkin.buyercryptomoney.data.repositories

import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository

class BuyRepositoryImpl(
    private val networkAPI: NetworkAPI,
    private val buyMapper: BuyMapper
) : BuyRepository {

    override suspend fun buy(buyModel: BuyModel) {
        networkAPI.buy(buyDTO = buyMapper.map(buyModel = buyModel))
    }
}
