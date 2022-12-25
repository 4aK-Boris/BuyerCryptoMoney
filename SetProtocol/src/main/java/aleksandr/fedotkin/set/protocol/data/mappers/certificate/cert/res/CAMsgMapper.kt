package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.res.CAMsg
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res.CAMsgModel

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
