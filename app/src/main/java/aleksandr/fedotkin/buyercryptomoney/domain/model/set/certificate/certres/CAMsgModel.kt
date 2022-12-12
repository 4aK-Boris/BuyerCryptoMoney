package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres.CardCurrency

data class CAMsgModel(
    val cardLogoURL: String?,
    val brandLogoURL: String,
    val cardCurrency: CardCurrency?,
    val cardholderMsg: String?
)
