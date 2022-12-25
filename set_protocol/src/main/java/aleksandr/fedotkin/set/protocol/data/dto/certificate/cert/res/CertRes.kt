package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EncK
import kotlinx.serialization.Serializable

@Serializable
data class CertRes(
    val signature: String,
    val encK: EncK
): DTO
