package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.BaseByteArrayMapperTest
import org.koin.core.component.inject

class Base64MapperTest : BaseByteArrayMapperTest<String>() {

    override val mapper by inject<Base64Mapper>()

    override suspend fun generateModel(): ByteArray {
        return generateByteArray(size = SIZE)
    }

    companion object {
        private const val SIZE = 1024
    }
}