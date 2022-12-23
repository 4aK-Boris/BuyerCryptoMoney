package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import kotlinx.serialization.KSerializer

interface CardCInitResRepository {

    val serializer: KSerializer<CardCInitRes>

    val convertToModel: (CardCInitRes) -> CardCInitResModel

    val convertToDTO: (CardCInitResModel) -> CardCInitRes
}
