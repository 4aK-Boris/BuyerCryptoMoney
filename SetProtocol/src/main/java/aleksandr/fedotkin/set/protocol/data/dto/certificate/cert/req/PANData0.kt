package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.datetime.LocalDate

@kotlinx.serialization.Serializable
data class PANData0(
    val pan: String,
    val cardExpiry: LocalDate,
    val cardSecret: String,
    val exNonce: String
): DTO
