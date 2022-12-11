package aleksandr.fedotkin.buyercryptomoney.data.di.set.repository

import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.CertificateRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.KeyRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.MessageDigestRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.SignatureRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.ErrorRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CertificateRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.MessageDigestRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.SignatureRepository
import android.system.Os.bind
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val setRepositoryModule = module {

    factoryOf(::CertificateRepositoryImpl) {
        bind<CertificateRepository>()
    }

    factoryOf(::KeyRepositoryImpl) {
        bind<KeyRepository>()
    }

    factoryOf(::SignatureRepositoryImpl) {
        bind<SignatureRepository>()
    }

    factoryOf(::MessageDigestRepositoryImpl) {
        bind<MessageDigestRepository>()
    }

    factoryOf(::ErrorRepositoryImpl) {
        bind<ErrorRepository>()
    }
}