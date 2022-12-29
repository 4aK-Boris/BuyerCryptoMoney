package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.GCA
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.PCA
import java.security.KeyPair
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val qualifier = named(PCA)

val pcaCertificateModule = module {

    single(qualifier = qualifier) {
        get<CryptoBuilder>().generatePairKey(keySize = PCA.keySize)
    }

    single(qualifier = qualifier) {
        get<CryptoBuilder>().createCertificate(
            keyPair = get(qualifier = qualifier),
            name = PCA.title,
            builder = get(),
            certificate = get<Certificate>(qualifier = named(GCA)).x509Certificate
        )
    }

    single(qualifier = qualifier) {
        get<KeyPair>(qualifier = qualifier).private
    }
}