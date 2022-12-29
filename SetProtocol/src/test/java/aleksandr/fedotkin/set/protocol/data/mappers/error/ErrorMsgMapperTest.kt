package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorMsg
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapperTest
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ErrorMsgMapperTest : BaseObjectMapperTest<ErrorMsgModel<TestModel>, ErrorMsg<Test>>() {

    override val mapper by inject<ErrorMsgMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): ErrorMsgModel<TestModel> {
        return ErrorMsgModel(
            messageHeader = messageHeaderMapperTest.generateModel(),
            badWrapper = generateTestModel()
        )
    }

    private val messageHeaderMapperTest by inject<MessageHeaderMapperTest>()
}