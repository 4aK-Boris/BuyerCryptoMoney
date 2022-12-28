package aleksandr.fedotkin.set.protocol.data.mappers.core.json

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.mapper.BaseMapperTest
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class JsonModelMapperTest : BaseMapperTest<TestModel, String>() {

    override val mapper by inject<JsonModelMapper<TestModel, Test>> { parametersOf(testMapper) }

    override suspend fun generateModel(): TestModel {
        return generateTestModel()
    }
}