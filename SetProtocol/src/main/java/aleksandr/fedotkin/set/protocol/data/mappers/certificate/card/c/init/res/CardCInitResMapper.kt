package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel

class CardCInitResMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val cardCInitResTBSMapper: CardCInitResTBSMapper
) {

    fun map(dto: CardCInitRes): CardCInitResModel {
        return CardCInitResModel(
            signature = byteArrayMapper.map(string = dto.signature),
            cardCInitResTBS = cardCInitResTBSMapper.map(dto = dto.cardCInitResTBS)
        )
    }

    fun map(model: CardCInitResModel): CardCInitRes {
        return CardCInitRes(
            signature = byteArrayMapper.map(byteArray = model.signature),
            cardCInitResTBS = cardCInitResTBSMapper.map(model = model.cardCInitResTBS)
        )
    }
}
