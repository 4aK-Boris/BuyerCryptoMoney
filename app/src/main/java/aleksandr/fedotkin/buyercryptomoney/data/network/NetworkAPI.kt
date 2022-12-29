package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.BuyerDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.ProductDTO
import aleksandr.fedotkin.buyercryptomoney.data.dto.SellerDTO

interface NetworkAPI {
    suspend fun getBuyers(): List<BuyerDTO>

    suspend fun getSeller(sellerId: Int): SellerDTO

    suspend fun getProducts(): List<ProductDTO>

    suspend fun getProduct(productId: Int): ProductDTO

    suspend fun buy(buyDTO: BuyDTO)

    suspend fun getCode(): Boolean

    suspend fun checkCode(code: Int): Boolean
}
