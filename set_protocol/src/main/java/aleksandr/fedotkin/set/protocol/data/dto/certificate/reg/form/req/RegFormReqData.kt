package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.certificate.RequestType

@kotlinx.serialization.Serializable
data class RegFormReqData(
    val rrpID: String,
    val lidEE: String,
    val challEE2: String,
    val lidCA: String,
    val requestType: RequestType,
    val language: Language,
    val thumbs: List<String>
): DTO