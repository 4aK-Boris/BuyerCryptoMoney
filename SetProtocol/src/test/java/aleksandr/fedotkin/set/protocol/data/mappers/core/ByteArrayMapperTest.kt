package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.BaseMapperTest
import org.koin.core.component.inject

class ByteArrayMapperTest : BaseMapperTest<String, ByteArray>() {

    override val mapper by inject<ByteArrayMapper>()

    override suspend fun generateModel(): String {
        return "e832-84671 824e9832-ne9327421e9 0324 /+*34+-*t54/*/3*2-5*213234/"
    }
}