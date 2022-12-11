package aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto

import aleksandr.fedotkin.buyercryptomoney.core.X509
import java.security.cert.CertificateFactory
import org.koin.dsl.module

val certificateModule = module {

    single<CertificateFactory> {
        CertificateFactory.getInstance(X509)
    }
}