package aleksandr.fedotkin.buyercryptomoney.data.repositories

import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyerMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.models.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyerRepository

class BuyerRepositoryImpl(
    private val buyerMapper: BuyerMapper,
    private val networkAPI: NetworkAPI
) : BuyerRepository {
    override suspend fun getBuyers(): List<BuyerModel> {
        return networkAPI.getBuyers().map { buyerDTO -> buyerMapper.map(buyerDTO = buyerDTO) }
    }
}
