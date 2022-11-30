package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.BUY_URL
import aleksandr.fedotkin.buyercryptomoney.GET_BUYERS_URL
import aleksandr.fedotkin.buyercryptomoney.GET_SELLER_URL
import aleksandr.fedotkin.buyercryptomoney.GET_SNIPPETS_URL
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.PurchaseDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SnippetDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.UpdateBuyerDTO

class NetworkAPIImpl(private val ktorClient: KtorClient): NetworkAPI {

    override suspend fun getBuyers(): List<BuyerDTO> {
        return ktorClient.get(url = GET_BUYERS_URL)
    }


    override suspend fun getSeller(id: Int): SellerDTO {
        return ktorClient.get(url = GET_SELLER_URL, parameters = mapOf("id" to id))
    }

    override suspend fun getSnippets(): List<SnippetDTO> {
        return ktorClient.get(url = GET_SNIPPETS_URL)
    }

    override suspend fun buy(purchase: PurchaseDTO): UpdateBuyerDTO {
        return ktorClient.post(url = BUY_URL, body = purchase)
    }
}