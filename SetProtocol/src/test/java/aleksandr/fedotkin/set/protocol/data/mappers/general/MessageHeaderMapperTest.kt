package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageHeader
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import org.koin.test.inject

class MessageHeaderMapperTest : BaseObjectMapperTest<MessageHeaderModel, MessageHeader>() {

    override val mapper by inject<MessageHeaderMapper>()

    override suspend fun generateModel(): MessageHeaderModel {
        return MessageHeaderModel(
            messageIDModel = messageIDMapperTest.generateModel(),
            rrpId = generateNewNumber()
        )
    }

    private val messageIDMapperTest by inject<MessageIDMapperTest>()
}