package aleksandr.fedotkin.set.protocol.data.di.crypto

import aleksandr.fedotkin.buyercryptomoney.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.buyercryptomoney.core.RSA
import javax.crypto.Cipher
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val cipherModule = module {

    single(qualifier = qualifier(name = RSA)) {
        Cipher.getInstance(RSA)
    }

    single(qualifier = qualifier(name = CIPHER_ALGORITHM)) {
        Cipher.getInstance(CIPHER_ALGORITHM)
    }
}
