package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.core.BUY_URL
import aleksandr.fedotkin.buyercryptomoney.core.GET_BUYERS_URL
import aleksandr.fedotkin.buyercryptomoney.core.GET_SELLER_URL
import aleksandr.fedotkin.buyercryptomoney.core.GET_SNIPPETS_URL
import aleksandr.fedotkin.buyercryptomoney.core.SELLER_ID
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO
import aleksandr.fedotkin.network.network.KtorClient

class NetworkAPIImpl(private val ktorClient: KtorClient) : NetworkAPI {

    override suspend fun getBuyers(): List<BuyerDTO> {
        return ktorClient.get(url = GET_BUYERS_URL)
    }

    override suspend fun getSeller(sellerId: Int): SellerDTO {
        return ktorClient.get(url = GET_SELLER_URL, parameters = mapOf(SELLER_ID to sellerId))
    }

    override suspend fun getSnippets(): List<ProductDTO> {
        return ktorClient.get(url = GET_SNIPPETS_URL)
    }

    override suspend fun buy(buyDTO: BuyDTO) {
        return ktorClient.post(url = BUY_URL, body = buyDTO)
    }
}
