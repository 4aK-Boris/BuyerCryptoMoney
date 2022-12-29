package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class MerchantAcquirerID(
    val merchantBIN: String,
    val merchantID: String,
): DTO
