package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateTimeMapper

class PANData0Mapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val dateTimeMapper: DateTimeMapper
) {

//    fun map(model: PANData0Model): PANData0 {
//        return PANData0(
//            pan = bigIntegerMapper.map(number = model.pan),
//            cardExpiry = dateTimeMapper.map(date = model.cardExpiry),
//            cardSecret = bigIntegerMapper.map(number = model.cardSecret),
//            exNonce = bigIntegerMapper.map(number = model.exNonce)
//        )
//    }
//
//    fun map(dto: PANData0): PANData0Model {
//        return PANData0Model(
//            pan = bigIntegerMapper.map(string = dto.pan),
//            cardExpiry = dateTimeMapper.map(date = dto.cardExpiry),
//            cardSecret = bigIntegerMapper.map(string = dto.cardSecret),
//            exNonce = bigIntegerMapper.map(string = dto.exNonce)
//        )
//    }
}
