package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import org.koin.test.inject

class CardCInitReqMapperTest: BaseObjectMapperTest<CardCInitReqModel, CardCInitReq>() {

    override val mapper by inject<CardCInitReqMapper>()

    override suspend fun generateModel(): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            brandID = generateNewNumber(),
            thumbs = listOf(generateByteArray(size = 1024))
        )
    }
}