package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitReq
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel

class CardCInitReqMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(cardCInitReqModel: CardCInitReqModel): CardCInitReq {
        return CardCInitReq(
            rrpID = bigIntegerMapper.map(number = cardCInitReqModel.rrpID),
            lidEE = bigIntegerMapper.map(number = cardCInitReqModel.lidEE),
            challEE = bigIntegerMapper.map(number = cardCInitReqModel.challEE),
            brandID = bigIntegerMapper.map(number = cardCInitReqModel.brandID),
            thumbs = cardCInitReqModel.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }

    fun map(cardCInitReq: CardCInitReq): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = bigIntegerMapper.map(string = cardCInitReq.rrpID),
            lidEE = bigIntegerMapper.map(string = cardCInitReq.lidEE),
            challEE = bigIntegerMapper.map(string = cardCInitReq.challEE),
            brandID = bigIntegerMapper.map(string = cardCInitReq.brandID),
            thumbs = cardCInitReq.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }
}