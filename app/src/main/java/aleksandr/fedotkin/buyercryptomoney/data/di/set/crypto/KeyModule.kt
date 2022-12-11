package aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto

import aleksandr.fedotkin.buyercryptomoney.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.buyercryptomoney.core.RSA
import java.security.KeyFactory
import java.security.KeyPairGenerator
import javax.crypto.KeyGenerator
import org.koin.dsl.module

val keyModule = module {

    single<KeyFactory> {
        KeyFactory.getInstance(RSA)
    }

    single<KeyGenerator> {
        KeyGenerator.getInstance(CIPHER_ALGORITHM)
    }

    single<KeyPairGenerator> {
        KeyPairGenerator.getInstance(RSA)
    }
}