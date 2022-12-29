package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers.RCA
import java.security.KeyPair
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val qualifier = named(RCA)

val rcaCertificateModule = module {

    single(qualifier = qualifier) {
        get<CryptoBuilder>().generatePairKey(keySize = RCA.keySize)
    }

    single(qualifier = qualifier) {
        get<CryptoBuilder>().createRootCertificate(
            keyPair = get(qualifier = qualifier),
            builder = get(),
            name = RCA.title
        )
    }

    single(qualifier = qualifier) {
        get<KeyPair>(qualifier = qualifier).private
    }
}


