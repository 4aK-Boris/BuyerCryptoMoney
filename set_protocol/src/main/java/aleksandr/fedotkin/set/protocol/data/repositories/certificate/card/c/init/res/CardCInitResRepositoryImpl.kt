package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository

class CardCInitResRepositoryImpl(
    private val cardCInitResMapper: CardCInitResMapper
) : CardCInitResRepository {

    override val serializer = CardCInitRes.serializer()

    override val convertToModel: (CardCInitRes) -> CardCInitResModel = cardCInitResMapper::map

    override val convertToDTO: (CardCInitResModel) -> CardCInitRes = cardCInitResMapper::map
}
