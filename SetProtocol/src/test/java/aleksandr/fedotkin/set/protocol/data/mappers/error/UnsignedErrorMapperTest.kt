package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.error.UnsignedError
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class UnsignedErrorMapperTest: BaseObjectMapperTest<UnsignedErrorModel<TestModel>, UnsignedError<Test>>() {

    override val mapper by inject<UnsignedErrorMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): UnsignedErrorModel<TestModel> {
        return UnsignedErrorModel(errorTBS = errorTBSMapperTest.generateModel())
    }

    private val errorTBSMapperTest by inject<ErrorTBSMapperTest>()
}