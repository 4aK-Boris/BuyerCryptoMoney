package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.SellerModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.SnippetModel

interface DataRepository {
    suspend fun getBuyers(): List<BuyerModel>
    suspend fun getSeller(id: Int): SellerModel
    suspend fun getSnippets(): List<SnippetModel>
}