package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.BaseRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository

class CardCInitReqRepositoryImpl(private val cardCInitReqMapper: CardCInitReqMapper) : CardCInitReqRepository, BaseRepository() {

    override val serializer = CardCInitReq.serializer()

    override val convertToDTO: (CardCInitReqModel) -> CardCInitReq = cardCInitReqMapper::map

    override val convertToModel: (CardCInitReq) -> CardCInitReqModel = cardCInitReqMapper::map
    override suspend fun createCardCInitReqModel(): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            brandID = generateNewNumber(),
            thumbs = emptyList()
        )
    }
}
