package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageIDRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageWrapperRepositoryTest
import org.koin.dsl.module

val generalRepositoryModule = module {

    factory {
        MessageHeaderRepositoryTest()
    }

    factory {
        MessageIDRepositoryTest()
    }

    factory {
        MessageWrapperRepositoryTest()
    }
}