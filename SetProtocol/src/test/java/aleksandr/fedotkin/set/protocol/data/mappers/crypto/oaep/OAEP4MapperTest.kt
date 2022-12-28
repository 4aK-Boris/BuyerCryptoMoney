package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP4
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP4Model
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class OAEP4MapperTest : BaseObjectMapperTest<OAEP4Model<TestModel>, OAEP4<Test>>() {

    override val mapper by inject<OAEP4Mapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): OAEP4Model<TestModel> {
        return OAEP4Model(
            secretKey = generateSecretKey(),
            hash = generateByteArray(size = 32),
            data = generateTestModel()
        )
    }
}