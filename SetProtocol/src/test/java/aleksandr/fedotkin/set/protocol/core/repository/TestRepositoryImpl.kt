package aleksandr.fedotkin.set.protocol.core.repository

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestMapper
import aleksandr.fedotkin.set.protocol.core.TestModel

class TestRepositoryImpl(
    override val mapper: TestMapper
) : TestRepository {

    override suspend fun createDTO(): Test {
        return mapper.map(createModel())
    }

    override suspend fun createModel(): TestModel {
        return TestModel(number = generateNewNumber(), array = generateByteArray(size = 128))
    }
}