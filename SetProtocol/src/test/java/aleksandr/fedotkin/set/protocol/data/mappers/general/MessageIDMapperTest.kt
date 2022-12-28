package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageID
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel
import org.koin.test.inject

class MessageIDMapperTest : BaseObjectMapperTest<MessageIDModel, MessageID>() {

    override val mapper by inject<MessageIDMapper>()

    override suspend fun generateModel(): MessageIDModel {
        return MessageIDModel(
            lIdC = generateNewNumber(),
            lIdM = generateNewNumber(),
            xId = generateNewNumber()
        )
    }
}