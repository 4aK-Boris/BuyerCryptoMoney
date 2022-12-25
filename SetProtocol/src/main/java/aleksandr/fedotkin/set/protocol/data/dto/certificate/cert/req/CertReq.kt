package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.Enc
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EncX
import kotlinx.serialization.Serializable

@Serializable
data class CertReq(
    val encX: EncX,
    val enc: Enc
): DTO
