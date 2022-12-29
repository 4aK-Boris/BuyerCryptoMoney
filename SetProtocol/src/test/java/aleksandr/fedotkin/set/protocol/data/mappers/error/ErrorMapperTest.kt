package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ErrorMapperTest: BaseObjectMapperTest<ErrorModel<TestModel>, Error<Test>>() {

    override val mapper by inject<ErrorMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): ErrorModel<TestModel> {
        return ErrorModel(
            signedError = signedErrorMapperTest.generateModel(),
            unsignedError = unsignedErrorMapperTest.generateModel()
        )
    }

    private val unsignedErrorMapperTest by inject<UnsignedErrorMapperTest>()
    private val signedErrorMapperTest by inject<SignedErrorMapperTest>()
}