package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitResTBS
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import kotlinx.serialization.KSerializer

class CardCInitResTBSMapper(
    private val base64Mapper: Base64Mapper,
    private val bigIntegerMapper: BigIntegerMapper
): SetMapper<CardCInitResTBSModel, CardCInitResTBS> {

    override val serializer: KSerializer<CardCInitResTBS>
        get() = CardCInitResTBS.serializer()

    override fun map(value: CardCInitResTBSModel): CardCInitResTBS {
        return CardCInitResTBS(
            rrpID = bigIntegerMapper.map(value = value.rrpID),
            lidEE = bigIntegerMapper.map(value= value.lidEE),
            challEE = bigIntegerMapper.map(value = value.challEE),
            lidCA = bigIntegerMapper.map(value = value.lidCA),
            caeThumb = base64Mapper.map(value = value.caeThumb),
            brandCRLIdentifier = value.brandCRLIdentifier.map { base64Mapper.map(value = it) },
            thumbs = value.thumbs.map { base64Mapper.map(value = it) }
        )
    }

    override fun reverseMap(value: CardCInitResTBS): CardCInitResTBSModel {
        return CardCInitResTBSModel(
            rrpID = bigIntegerMapper.reverseMap(value = value.rrpID),
            lidEE = bigIntegerMapper.reverseMap(value = value.lidEE),
            challEE = bigIntegerMapper.reverseMap(value = value.challEE),
            lidCA = bigIntegerMapper.reverseMap(value = value.lidCA),
            caeThumb = base64Mapper.reverseMap(value = value.caeThumb),
            brandCRLIdentifier = value.brandCRLIdentifier.map { base64Mapper.reverseMap(value = it) },
            thumbs = value.thumbs.map { base64Mapper.reverseMap(value = it) }
        )
    }
}
