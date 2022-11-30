package aleksandr.fedotkin.buyercryptomoney.data.repositories

import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.PurchaseMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.model.PurchaseModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.UpdateBuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository

class BuyRepositoryImpl(
    private val networkAPI: NetworkAPI,
    private val purchaseMapper: PurchaseMapper,
    private val buyerMapper: BuyerMapper
) : BuyRepository {

    override suspend fun buy(purchaseModel: PurchaseModel): UpdateBuyerModel {
        val purchaseDTO = purchaseMapper.map(purchaseModel = purchaseModel)
        val updateBuyerDTO = networkAPI.buy(purchaseDTO = purchaseDTO)
        return buyerMapper.map(updateBuyerDTO = updateBuyerDTO)
    }
}