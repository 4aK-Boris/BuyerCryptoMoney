package aleksandr.fedotkin.buyercryptomoney.data.dto

@kotlinx.serialization.Serializable
data class UpdateBuyerDTO(val id: Int, val amountOfMoney: Int)