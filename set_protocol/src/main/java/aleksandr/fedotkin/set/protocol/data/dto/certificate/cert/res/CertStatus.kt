package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class CertStatus(
    val certStatusCode: CertStatusCode,
    val nonceCCA: String?,
    val eeMessage: String,
    val caMsg: CAMsg,
    val failedItemSeq: List<FailedItem>
): DTO
