package aleksandr.fedotkin.buyercryptomoney.data.di.set.repository

import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.ErrorRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.MessageWrapperRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.CardCInitReqRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.CardCInitResRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.KeyRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.MessageDigestRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.SignatureRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitReqRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitResRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.MessageDigestRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.SignatureRepository
import org.koin.dsl.module

val setRepositoryModule = module {

//    factory<CertificateRepository> {
//        CertificateRepositoryImpl(
//
//        )
//    }

    factory<KeyRepository> {
        KeyRepositoryImpl(
            keyFactory = get(),
            keyPairGenerator = get(),
            keyGenerator = get(),
            certificateFactory = get()
        )
    }

    factory<SignatureRepository> {
        SignatureRepositoryImpl(
            signature = get(),
            jsonMapper = get(),
            secureRandom = get()
        )
    }

    factory<MessageDigestRepository> {
        MessageDigestRepositoryImpl(
            messageDigest = get(),
            jsonMapper = get()
        )
    }

    factory<ErrorRepository> {
        ErrorRepositoryImpl(
            errorMapper = get(),
            signatureRepository = get(),
            keyRepository = get(),
            errorTBSMapper = get()
        )
    }

    factory<MessageWrapperRepository> {
        MessageWrapperRepositoryImpl(
            errorRepository = get(),
            messageWrapperMapper = get(),
            jsonMapper = get(),
            messageHeaderMapper = get()
        )
    }

    factory<CardCInitReqRepository> {
        CardCInitReqRepositoryImpl(
            messageWrapperRepository = get(),
            jsonMapper = get(),
            cardCInitReqMapper = get()
        )
    }

    factory<CardCInitResRepository> {
        CardCInitResRepositoryImpl(
            cardCInitResMapper = get()
        )
    }
}
