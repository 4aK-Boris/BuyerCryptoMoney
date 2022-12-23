package aleksandr.fedotkin.buyercryptomoney.data.repositories

import aleksandr.fedotkin.buyercryptomoney.data.mappers.SellerMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.models.SellerModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.SellerRepository

class SellerRepositoryImpl(
    private val sellerMapper: SellerMapper,
    private val networkAPI: NetworkAPI
) : SellerRepository {

    override suspend fun getSeller(sellerId: Int): SellerModel {
        return sellerMapper.map(networkAPI.getSeller(sellerId = sellerId))
    }
}
