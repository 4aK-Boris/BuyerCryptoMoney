package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.core.AsymmetricCipherRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.KeyRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.MessageDigestRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.SignatureRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.core.SymmetricCipherRepositoryTest
import org.koin.dsl.module

val coreRepositoryTestModule = module {

    factory {
        AsymmetricCipherRepositoryTest()
    }

    factory {
        KeyRepositoryTest()
    }

    factory {
        MessageDigestRepositoryTest()
    }

    factory {
        SignatureRepositoryTest()
    }

    factory {
        SymmetricCipherRepositoryTest()
    }
}