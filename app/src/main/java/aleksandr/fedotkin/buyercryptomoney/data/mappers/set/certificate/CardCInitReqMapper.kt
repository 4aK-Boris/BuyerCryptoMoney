package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitReq
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel

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
