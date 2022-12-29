package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CardCurrency
import aleksandr.fedotkin.set.protocol.core.Model

data class CAMsgModel(
    val cardLogoURL: String?,
    val brandLogoURL: String,
    val cardCurrency: CardCurrency?,
    val cardholderMsg: String?
) : Model
