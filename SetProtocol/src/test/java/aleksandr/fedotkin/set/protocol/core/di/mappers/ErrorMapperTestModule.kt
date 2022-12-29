package aleksandr.fedotkin.set.protocol.core.di.mappers

import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMsgMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorTBSMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.error.SignedErrorMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.error.UnsignedErrorMapperTest
import org.koin.dsl.module

val errorMapperTestModule = module {

    factory {
        SignedErrorMapperTest()
    }

    factory {
        ErrorMsgMapperTest()
    }

    factory {
        ErrorTBSMapperTest()
    }

    factory {
        UnsignedErrorMapperTest()
    }

    factory {
        ErrorMapperTest()
    }
}