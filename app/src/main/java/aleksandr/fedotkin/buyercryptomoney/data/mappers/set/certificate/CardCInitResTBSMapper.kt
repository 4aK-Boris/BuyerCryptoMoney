package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitResTBS
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResTBSModel

class CardCInitResTBSMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(cardCInitResTBS: CardCInitResTBS): CardCInitResTBSModel {
        return CardCInitResTBSModel(
            rrpID = bigIntegerMapper.map(string = cardCInitResTBS.rrpID),
            lidEE = bigIntegerMapper.map(string = cardCInitResTBS.lidEE),
            challEE = bigIntegerMapper.map(string = cardCInitResTBS.challEE),
            lidCA = bigIntegerMapper.map(string = cardCInitResTBS.lidCA),
            caeThumb = byteArrayMapper.map(string = cardCInitResTBS.caeThumb),
            brandCRLIdentifier = cardCInitResTBS.brandCRLIdentifier.map { byteArrayMapper.map(string = it) },
            thumbs = cardCInitResTBS.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }

    fun map(cardCInitResTBSModel: CardCInitResTBSModel): CardCInitResTBS {
        return CardCInitResTBS(
            rrpID = bigIntegerMapper.map(number = cardCInitResTBSModel.rrpID),
            lidEE = bigIntegerMapper.map(number= cardCInitResTBSModel.lidEE),
            challEE = bigIntegerMapper.map(number = cardCInitResTBSModel.challEE),
            lidCA = bigIntegerMapper.map(number = cardCInitResTBSModel.lidCA),
            caeThumb = byteArrayMapper.map(byteArray = cardCInitResTBSModel.caeThumb),
            brandCRLIdentifier = cardCInitResTBSModel.brandCRLIdentifier.map { byteArrayMapper.map(byteArray = it) },
            thumbs = cardCInitResTBSModel.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }
}