package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.model.SellerModel

interface SellerRepository {

    suspend fun getSeller(sellerId: Int): SellerModel
}
