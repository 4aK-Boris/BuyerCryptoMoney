package aleksandr.fedotkin.set.protocol.data.di.crypto

import java.security.SecureRandom
import org.koin.dsl.module

val cryptoModule = module {

    includes(certificateModule, keyModule, signatureModule, cipherModule, messageDigestModule)

    single {
        SecureRandom()
    }
}
