package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.BCA
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.GCA
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.RCA
import java.security.KeyPair
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val qualifier = named(GCA)

val gcaCertificateModule = module {

    single(qualifier = qualifier) {
        get<CryptoBuilder>().generatePairKey(keySize = RCA.keySize)
    }

    single(qualifier = qualifier) {
        get<CryptoBuilder>().createCertificate(
            keyPair = get(qualifier = qualifier),
            name = GCA.title,
            certificate = get<Certificate>(qualifier = named(BCA)).x509Certificate,
            builder = get()
        )
    }

    single(qualifier = qualifier) {
        get<KeyPair>(qualifier = qualifier).private
    }
}