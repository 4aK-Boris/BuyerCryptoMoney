package aleksandr.fedotkin.set.protocol.core

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import kotlinx.serialization.KSerializer

class TestMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val base64Mapper: Base64Mapper
): SetMapper<TestModel, Test> {

    override val serializer: KSerializer<Test>
        get() = Test.serializer()

    override fun map(value: TestModel): Test {
        return Test(
            number = bigIntegerMapper.map(value = value.number),
            array = base64Mapper.map(value = value.array)
        )
    }

    override fun reverseMap(value: Test): TestModel {
        return TestModel(
            number = bigIntegerMapper.reverseMap(value = value.number),
            array = base64Mapper.reverseMap(value = value.array)
        )
    }
}