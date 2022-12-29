package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import java.math.BigInteger

interface CardCInitReqRepository : BaseSetRepository<CardCInitReqModel, CardCInitReq> {
    suspend fun createCardCInitReqModel(rrpid: BigInteger): CardCInitReqModel
}
