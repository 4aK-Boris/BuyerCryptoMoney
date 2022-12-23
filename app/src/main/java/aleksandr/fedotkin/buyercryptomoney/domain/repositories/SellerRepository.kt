package aleksandr.fedotkin.buyercryptomoney.domain.repositories

import aleksandr.fedotkin.buyercryptomoney.domain.models.SellerModel

interface SellerRepository {

    suspend fun getSeller(sellerId: Int): SellerModel
}
