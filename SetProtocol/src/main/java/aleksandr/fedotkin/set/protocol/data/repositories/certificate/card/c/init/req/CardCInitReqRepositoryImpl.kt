package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import java.math.BigInteger

class CardCInitReqRepositoryImpl(
    override val mapper: CardCInitReqMapper
) : CardCInitReqRepository {

      override suspend fun createCardCInitReqModel(rrpid: BigInteger): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = rrpid,
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            brandID = generateNewNumber(),
            thumbs = emptyList()
        )
    }
}
