package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import kotlinx.serialization.KSerializer

class CardCInitReqMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val base64Mapper: Base64Mapper
): SetMapper<CardCInitReqModel, CardCInitReq> {

    override val serializer: KSerializer<CardCInitReq>
        get() = CardCInitReq.serializer()

    override fun map(value: CardCInitReqModel): CardCInitReq {
        return CardCInitReq(
            rrpID = bigIntegerMapper.map(value = value.rrpID),
            lidEE = bigIntegerMapper.map(value = value.lidEE),
            challEE = bigIntegerMapper.map(value = value.challEE),
            brandID = bigIntegerMapper.map(value = value.brandID),
            thumbs = value.thumbs.map { base64Mapper.map(value = it) }
        )
    }

    override fun reverseMap(value: CardCInitReq): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = bigIntegerMapper.reverseMap(value = value.rrpID),
            lidEE = bigIntegerMapper.reverseMap(value = value.lidEE),
            challEE = bigIntegerMapper.reverseMap(value = value.challEE),
            brandID = bigIntegerMapper.reverseMap(value = value.brandID),
            thumbs = value.thumbs.map { base64Mapper.reverseMap(value = it) }
        )
    }
}
