package aleksandr.fedotkin.set.protocol.core.di.certificate

import java.security.SecureRandom
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val certificateModule = module {

    includes(
        bcaCertificateModule,
        rcaCertificateModule,
        ccaCertificateModule,
        mcaCertificateModule,
        pcaCertificateModule,
        gcaCertificateModule
    )

    factory {
        Certificate.Builder()
    }

    factoryOf(::CryptoBuilder)

    factory {
        SecureRandom()
    }
}
