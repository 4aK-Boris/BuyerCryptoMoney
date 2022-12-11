package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitResTBS
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResTBSModel

class CardCInitResTBSMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(dto: CardCInitResTBS): CardCInitResTBSModel {
        return CardCInitResTBSModel(
            rrpID = bigIntegerMapper.map(string = dto.rrpID),
            lidEE = bigIntegerMapper.map(string = dto.lidEE),
            challEE = bigIntegerMapper.map(string = dto.challEE),
            lidCA = bigIntegerMapper.map(string = dto.lidCA),
            caeThumb = byteArrayMapper.map(string = dto.caeThumb),
            brandCRLIdentifier = dto.brandCRLIdentifier.map { byteArrayMapper.map(string = it) },
            thumbs = dto.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }

    fun map(model: CardCInitResTBSModel): CardCInitResTBS {
        return CardCInitResTBS(
            rrpID = bigIntegerMapper.map(number = model.rrpID),
            lidEE = bigIntegerMapper.map(number= model.lidEE),
            challEE = bigIntegerMapper.map(number = model.challEE),
            lidCA = bigIntegerMapper.map(number = model.lidCA),
            caeThumb = byteArrayMapper.map(byteArray = model.caeThumb),
            brandCRLIdentifier = model.brandCRLIdentifier.map { byteArrayMapper.map(byteArray = it) },
            thumbs = model.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }
}
