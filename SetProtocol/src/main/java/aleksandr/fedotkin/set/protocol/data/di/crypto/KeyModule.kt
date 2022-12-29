package aleksandr.fedotkin.set.protocol.data.di.crypto

import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.RSA
import aleksandr.fedotkin.set.protocol.core.SYMMETRIC_KEY_LENGTH
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
        KeyGenerator.getInstance(CIPHER_ALGORITHM).apply {
            init(SYMMETRIC_KEY_LENGTH, get())
        }
    }

    single<KeyPairGenerator> {
        KeyPairGenerator.getInstance(RSA).apply {
            initialize(2048, get())
        }
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