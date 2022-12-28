package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ErrorTBSMapperTest : BaseObjectMapperTest<ErrorTBSModel<TestModel>, ErrorTBS<Test>>() {

    override val mapper by inject<ErrorTBSMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): ErrorTBSModel<TestModel> {
        return ErrorTBSModel(
            errorCode = ErrorCode.ThumbsMismatch,
            errorNonce = generateNewNumber(),
            errorOID = null,
            errorThumb = generateByteArray(size = 128),
            errorMsg = errorMsgMapperTest.generateModel()
        )
    }

    private val errorMsgMapperTest by inject<ErrorMsgMapperTest>()
}