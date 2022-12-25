package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel

class CardCInitReqMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(model: CardCInitReqModel): CardCInitReq {
        return CardCInitReq(
            rrpID = bigIntegerMapper.map(number = model.rrpID),
            lidEE = bigIntegerMapper.map(number = model.lidEE),
            challEE = bigIntegerMapper.map(number = model.challEE),
            brandID = bigIntegerMapper.map(number = model.brandID),
            thumbs = model.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }

    fun map(dto: CardCInitReq): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = bigIntegerMapper.map(string = dto.rrpID),
            lidEE = bigIntegerMapper.map(string = dto.lidEE),
            challEE = bigIntegerMapper.map(string = dto.challEE),
            brandID = bigIntegerMapper.map(string = dto.brandID),
            thumbs = dto.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }
}
