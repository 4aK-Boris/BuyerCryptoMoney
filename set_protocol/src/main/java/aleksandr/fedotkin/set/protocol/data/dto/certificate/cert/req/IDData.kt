package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class IDData(
    val merchantAcquirerID: MerchantAcquirerID,
    val acquirerID: AcquirerID
): DTO
