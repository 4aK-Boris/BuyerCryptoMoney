package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.BCA
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.RCA
import java.security.KeyPair
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val qualifier = named(BCA)

val bcaCertificateModule = module {

    single(qualifier = qualifier) {
        get<CryptoBuilder>().generatePairKey(keySize = BCA.keySize)
    }

    single(qualifier = qualifier) {
        get<CryptoBuilder>().createCertificate(
            keyPair = get(qualifier = qualifier),
            name = BCA.title,
            builder = get(),
            certificate = get<Certificate>(qualifier = named(RCA)).x509Certificate
        )
    }

    single(qualifier = qualifier) {
        get<KeyPair>(qualifier = qualifier).private
    }
}



