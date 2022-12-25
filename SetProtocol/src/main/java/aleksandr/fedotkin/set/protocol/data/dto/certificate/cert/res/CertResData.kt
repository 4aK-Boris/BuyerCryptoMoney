package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class CertResData(
    val rrpID: String,
    val lidEE: String,
    val challEE3: String,
    val lidCA: String,
    val certStatus: CertStatus,
    val certThumbs: List<String>,
    val brandCRLIdentifier: List<String>,
    val thumbs: List<String>
): DTO
