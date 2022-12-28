package aleksandr.fedotkin.set.protocol.data.mappers.core.json

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.mapper.BaseMapperTest
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class JsonByteArrayMapperTest : BaseMapperTest<Test, ByteArray>() {

    override val mapper by inject<JsonByteArrayMapper<Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): Test {
        return generateTestDTO()
    }
}