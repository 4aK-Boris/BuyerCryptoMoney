package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO

interface NetworkAPI {
    suspend fun getBuyers(): List<BuyerDTO>
    suspend fun getSeller(sellerId: Int): SellerDTO
    suspend fun getSnippets(): List<ProductDTO>
    suspend fun buy(buyDTO: BuyDTO)
}
