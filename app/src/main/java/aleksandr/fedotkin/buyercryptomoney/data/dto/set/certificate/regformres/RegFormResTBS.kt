package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RequestType

@kotlinx.serialization.Serializable
data class RegFormResTBS(
    val rrpID: String,
    val lidEE: String,
    val challEE2: String,
    val lidCA: String,
    val challCA: String,
    val caeThumb: String,
    val requestType: RequestType,
    val regFormOrReferral: RegFormOrReferral,
    val brandCRLIdentifier: List<String>,
    val thumbs: List<String>
)
