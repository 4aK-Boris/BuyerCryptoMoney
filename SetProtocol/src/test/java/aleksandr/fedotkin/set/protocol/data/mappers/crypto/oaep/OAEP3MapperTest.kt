package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP3
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP3Model
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class OAEP3MapperTest : BaseObjectMapperTest<OAEP3Model<TestModel>, OAEP3<Test>>() {

    override val mapper by inject<OAEP3Mapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): OAEP3Model<TestModel> {
        return OAEP3Model(secretKey = generateSecretKey(), data = generateTestModel())
    }
}