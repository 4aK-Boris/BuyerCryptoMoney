package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.error.ErrorMsgRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.ErrorRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.ErrorTBSRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.SignedErrorRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.error.UnsignedErrorRepositoryTest
import org.koin.dsl.module

val errorRepositoryTestModule = module {

    factory {
        ErrorMsgRepositoryTest()
    }

    factory {
        ErrorTBSRepositoryTest()
    }

    factory {
        SignedErrorRepositoryTest()
    }

    factory {
        ErrorRepositoryTest()
    }

    factory {
        UnsignedErrorRepositoryTest()
    }
}