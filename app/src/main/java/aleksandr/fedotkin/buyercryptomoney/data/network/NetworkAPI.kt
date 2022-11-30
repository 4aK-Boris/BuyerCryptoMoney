package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.PurchaseDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SnippetDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.UpdateBuyerDTO

interface NetworkAPI {
    suspend fun getBuyers(): List<BuyerDTO>
    suspend fun getSeller(id: Int): SellerDTO
    suspend fun getSnippets(): List<SnippetDTO>
    suspend fun buy(purchaseDTO: PurchaseDTO): UpdateBuyerDTO
}