package aleksandr.fedotkin.buyercryptomoney.data.repositories

import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.SellerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.SnippetMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.SellerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.SnippetModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.DataRepository

class DataRepositoryImpl(
    private val sellerMapper: SellerMapper,
    private val buyerMapper: BuyerMapper,
    private val snippetMapper: SnippetMapper,
    private val networkAPI: NetworkAPI
) : DataRepository {
    override suspend fun getBuyers(): List<BuyerModel> {
        return networkAPI.getBuyers().map { buyerDTO -> buyerMapper.map(buyerDTO = buyerDTO) }
    }

    override suspend fun getSeller(id: Int): SellerModel {
        return sellerMapper.map(networkAPI.getSeller(id))
    }

    override suspend fun getSnippets(): List<SnippetModel> {
        return networkAPI.getSnippets().map { snippetDTO -> snippetMapper.map(snippetDTO = snippetDTO) }
    }

}