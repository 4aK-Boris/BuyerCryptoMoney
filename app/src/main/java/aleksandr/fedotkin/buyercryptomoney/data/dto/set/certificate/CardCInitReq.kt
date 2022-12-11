package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate

import kotlinx.serialization.Serializable

@Serializable
data class CardCInitReq(
    val rrpID: String,
    val lidEE: String,
    val challEE: String,
    val brandID: String,
    val thumbs: List<String>
)
