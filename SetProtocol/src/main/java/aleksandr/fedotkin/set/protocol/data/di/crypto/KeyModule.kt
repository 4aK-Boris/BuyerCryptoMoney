package aleksandr.fedotkin.set.protocol.data.di.crypto

import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.RSA
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
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

    single<KeyPair> {
        get<KeyPairGenerator>().genKeyPair()
    }

    single<PublicKey> {
        get<KeyPair>().public
    }

    single<PrivateKey> {
        get<KeyPair>().private
    }
}