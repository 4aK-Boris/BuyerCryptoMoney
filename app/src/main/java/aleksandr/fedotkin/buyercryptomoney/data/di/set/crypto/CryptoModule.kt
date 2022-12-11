package aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto

import java.security.SecureRandom
import org.koin.dsl.module

val cryptoModule = module {

    includes(certificateModule, keyModule, signatureModule)

    single {
        SecureRandom()
    }
}