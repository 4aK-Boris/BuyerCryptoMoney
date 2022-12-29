package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class MessageWrapperMapperTest :
    BaseObjectMapperTest<MessageWrapperModel<TestModel>, MessageWrapper<Test>>() {

    override val mapper by inject<MessageWrapperMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): MessageWrapperModel<TestModel> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapperTest.generateModel(),
            messageModel = generateTestModel(),
            mWExtension = null
        )
    }

    private val messageHeaderMapperTest by inject<MessageHeaderMapperTest>()
}