package aleksandr.fedotkin.set.protocol.data.di.crypto

import aleksandr.fedotkin.set.protocol.core.X509
import java.security.cert.CertificateFactory
import org.koin.dsl.module

val certificateModule = module {

    single<CertificateFactory> {
        CertificateFactory.getInstance(X509)
    }
}