package aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class CAMsg(
    val cardLogoURL: String?,
    val brandLogoURL: String,
    val cardCurrency: CardCurrency?,
    val cardholderMsg: String?
): DTO
