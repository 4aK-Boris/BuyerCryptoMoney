package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.Language

@kotlinx.serialization.Serializable
data class RegFormReqData(
    val rrpID: String,
    val lidEE: String,
    val challEE2: String,
    val lidCA: String,
    val requestType: RequestType,
    val language: Language,
    val thumbs: List<String>
)