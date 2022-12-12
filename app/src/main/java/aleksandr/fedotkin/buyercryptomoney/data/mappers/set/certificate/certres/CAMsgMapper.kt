package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certres.CAMsg
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certres.CAMsgModel

class CAMsgMapper {

    fun map(model: CAMsgModel): CAMsg {
        return CAMsg(
            cardLogoURL = model.cardLogoURL,
            brandLogoURL = model.brandLogoURL,
            cardCurrency = model.cardCurrency,
            cardholderMsg = model.cardholderMsg
        )
    }

    fun map(dto: CAMsg): CAMsgModel {
        return CAMsgModel(
            cardLogoURL = dto.cardLogoURL,
            brandLogoURL = dto.brandLogoURL,
            cardCurrency = dto.cardCurrency,
            cardholderMsg = dto.cardholderMsg
        )
    }
}
