package aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitResTBS
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import org.koin.test.inject

class CardCInitResTBSMapperTest: BaseObjectMapperTest<CardCInitResTBSModel, CardCInitResTBS>() {

    override val mapper by inject<CardCInitResTBSMapper>()

    override suspend fun generateModel(): CardCInitResTBSModel {
        return CardCInitResTBSModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            lidCA = generateNewNumber(),
            caeThumb = generateByteArray(size = 256),
            brandCRLIdentifier = emptyList(),
            thumbs = listOf(generateByteArray(size = 32))
        )
    }
}