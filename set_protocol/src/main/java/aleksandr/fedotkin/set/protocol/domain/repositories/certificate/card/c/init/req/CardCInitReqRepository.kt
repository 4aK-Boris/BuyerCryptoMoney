package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import kotlinx.serialization.KSerializer

interface CardCInitReqRepository {

    val serializer: KSerializer<CardCInitReq>

    val convertToDTO: (CardCInitReqModel) -> CardCInitReq

    val convertToModel: (CardCInitReq) -> CardCInitReqModel
    suspend fun createCardCInitReqModel(): CardCInitReqModel
}
