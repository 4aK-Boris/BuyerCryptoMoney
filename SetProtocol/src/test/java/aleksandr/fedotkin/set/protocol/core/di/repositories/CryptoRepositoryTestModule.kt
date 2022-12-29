package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.crypto.EXHRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP2RepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP3RepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP4RepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEPRepositoryTest
import org.koin.dsl.module

val cryptoRepositoryTestModule = module {

    factory {
        OAEPRepositoryTest()
    }

    factory {
        OAEP2RepositoryTest()
    }

    factory {
        OAEP3RepositoryTest()
    }

    factory {
        OAEP4RepositoryTest()
    }

    factory {
        EXHRepositoryTest()
    }
}