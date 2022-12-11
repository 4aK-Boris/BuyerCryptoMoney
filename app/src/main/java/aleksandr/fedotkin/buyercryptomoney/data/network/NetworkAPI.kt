package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper

interface NetworkAPI {
    suspend fun getBuyers(): List<BuyerDTO>
    suspend fun getSeller(sellerId: Int): SellerDTO
    suspend fun getSnippets(): List<ProductDTO>
    suspend fun buy(buyDTO: BuyDTO)
    suspend fun cardCInit(messageWrapperJson: String): String
    suspend fun sendError(messageWrapperJson: String): Boolean
    suspend fun regForm(messageWrapperJson: String): String
}
