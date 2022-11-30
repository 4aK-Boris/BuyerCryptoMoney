package aleksandr.fedotkin.buyercryptomoney.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseDTO(
    val productDTO: ProductDTO,
    val bankCardDTO: BankCardDTO
)