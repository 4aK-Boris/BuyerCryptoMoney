package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class PublicKeySorE(
    val publicKeyS: String?,
    val publicKeyE: String?
): DTO
