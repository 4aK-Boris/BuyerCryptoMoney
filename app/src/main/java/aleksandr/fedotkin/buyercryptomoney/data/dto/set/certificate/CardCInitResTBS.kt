package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate

import kotlinx.serialization.Serializable

@Serializable
data class CardCInitResTBS(
    val rrpID: String,
    val lidEE: String,
    val challEE: String,
    val lidCA: String,
    val caeThumb: String,
    val brandCRLIdentifier: List<String>,
    val thumbs: List<String>
)
