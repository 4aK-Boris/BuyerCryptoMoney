package aleksandr.fedotkin.set.protocol.core.di.mappers

import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageIDMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageWrapperMapperTest
import org.koin.dsl.module

val generalMapperTestModule = module {

    factory {
        MessageIDMapperTest()
    }

    factory {
        MessageHeaderMapperTest()
    }

    factory {
        MessageWrapperMapperTest()
    }
}