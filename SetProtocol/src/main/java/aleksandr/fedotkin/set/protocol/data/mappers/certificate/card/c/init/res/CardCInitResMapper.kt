package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import kotlinx.serialization.KSerializer

class CardCInitResMapper(
    private val base64Mapper: Base64Mapper,
    private val cardCInitResTBSMapper: CardCInitResTBSMapper
): SetMapper<CardCInitResModel, CardCInitRes> {

    override val serializer: KSerializer<CardCInitRes>
        get() = CardCInitRes.serializer()

    override fun map(value: CardCInitResModel): CardCInitRes {
        return CardCInitRes(
            signature = base64Mapper.map(value = value.signature),
            cardCInitResTBS = cardCInitResTBSMapper.map(value = value.cardCInitResTBS)
        )
    }

    override fun reverseMap(value: CardCInitRes): CardCInitResModel {
        return CardCInitResModel(
            signature = base64Mapper.reverseMap(value = value.signature),
            cardCInitResTBS = cardCInitResTBSMapper.reverseMap(value = value.cardCInitResTBS)
        )
    }
}
