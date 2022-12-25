package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.RequestType

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
): DTO
