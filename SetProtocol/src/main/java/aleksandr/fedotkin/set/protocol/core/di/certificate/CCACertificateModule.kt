package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.CCA
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.GCA
import java.security.KeyPair
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val qualifier = named(CCA)

val ccaCertificateModule = module {

    single(qualifier = qualifier) {
        get<CryptoBuilder>().generatePairKey(keySize = CCA.keySize)
    }

    single(qualifier = qualifier) {
        get<CryptoBuilder>().createCertificate(
            keyPair = get(qualifier = qualifier),
            name = CCA.title,
            builder = get(),
            certificate = get<Certificate>(qualifier = named(GCA)).x509Certificate
        )
    }

    single(qualifier = qualifier) {
        get<KeyPair>(qualifier = qualifier).private
    }
}