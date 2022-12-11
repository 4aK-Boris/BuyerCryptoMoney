package aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto

import aleksandr.fedotkin.buyercryptomoney.core.SIGNATURE_ALGORITHM
import java.security.Signature
import org.koin.dsl.module

val signatureModule = module {

    single<Signature> {
        Signature.getInstance(SIGNATURE_ALGORITHM)
    }
}