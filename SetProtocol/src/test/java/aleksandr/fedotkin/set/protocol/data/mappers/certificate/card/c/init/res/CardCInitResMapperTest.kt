package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import org.koin.test.inject

class CardCInitResMapperTest: BaseObjectMapperTest<CardCInitResModel, CardCInitRes>() {

    override val mapper by inject<CardCInitResMapper>()

    override suspend fun generateModel(): CardCInitResModel {
        return CardCInitResModel(
            signature = generateByteArray(size = 32),
            cardCInitResTBS = cardCInitResTBSMapperTest.generateModel()
        )
    }

    private val cardCInitResTBSMapperTest by inject<CardCInitResTBSMapperTest>()
}