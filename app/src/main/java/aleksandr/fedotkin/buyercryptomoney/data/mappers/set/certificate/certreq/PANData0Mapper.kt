package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.PANData0
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.DateTimeMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.PANData0Model

class PANData0Mapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val dateTimeMapper: DateTimeMapper
) {

    fun map(model: PANData0Model): PANData0 {
        return PANData0(
            pan = bigIntegerMapper.map(number = model.pan),
            cardExpiry = dateTimeMapper.map(date = model.cardExpiry),
            cardSecret = bigIntegerMapper.map(number = model.cardSecret),
            exNonce = bigIntegerMapper.map(number = model.exNonce)
        )
    }

    fun map(dto: PANData0): PANData0Model {
        return PANData0Model(
            pan = bigIntegerMapper.map(string = dto.pan),
            cardExpiry = dateTimeMapper.map(date = dto.cardExpiry),
            cardSecret = bigIntegerMapper.map(string = dto.cardSecret),
            exNonce = bigIntegerMapper.map(string = dto.exNonce)
        )
    }
}
