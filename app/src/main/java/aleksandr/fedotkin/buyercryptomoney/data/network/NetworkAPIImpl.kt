package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.core.BUY_URL
import aleksandr.fedotkin.buyercryptomoney.core.CHECK_CODE
import aleksandr.fedotkin.buyercryptomoney.core.CODE
import aleksandr.fedotkin.buyercryptomoney.core.GET_BUYERS_URL
import aleksandr.fedotkin.buyercryptomoney.core.GET_PRODUCTS_URL
import aleksandr.fedotkin.buyercryptomoney.core.GET_PRODUCT_URL
import aleksandr.fedotkin.buyercryptomoney.core.GET_SELLER_URL
import aleksandr.fedotkin.buyercryptomoney.core.PRODUCT_ID
import aleksandr.fedotkin.buyercryptomoney.core.SELLER_ID
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO

class NetworkAPIImpl(private val ktorClient: KtorClient): NetworkAPI {

    override suspend fun getBuyers(): List<BuyerDTO> {
        return ktorClient.get(url = GET_BUYERS_URL)
    }


    override suspend fun getSeller(sellerId: Int): SellerDTO {
        return ktorClient.get(url = GET_SELLER_URL, parameters = mapOf(SELLER_ID to sellerId))
    }

    override suspend fun getProducts(): List<ProductDTO> {
        return ktorClient.get(url = GET_PRODUCTS_URL)
    }

    override suspend fun getProduct(productId: Int): ProductDTO {
        return ktorClient.get(url = GET_PRODUCT_URL, parameters = mapOf(PRODUCT_ID to productId))
    }

    override suspend fun buy(buyDTO: BuyDTO) {
        return ktorClient.post(url = BUY_URL, body = buyDTO)
    }

    override suspend fun getCode(): Boolean {
        return ktorClient.get(url = CODE)
    }

    override suspend fun checkCode(code: Int): Boolean {
        return ktorClient.post(url = CHECK_CODE, body = code)
    }
}
