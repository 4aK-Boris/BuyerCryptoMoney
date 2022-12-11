package aleksandr.fedotkin.buyercryptomoney.data.di.set.repository

import aleksandr.fedotkin.buyercryptomoney.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.buyercryptomoney.core.RSA
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.ErrorRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.MessageWrapperRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.CardCInitReqRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.CardCInitResRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.PANOnlyRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.RegFormReqDataRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate.RegFormReqRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.CipherRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.CryptoDataRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.EXHRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.KeyRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.MessageDigestRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.OAEPRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto.SignatureRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitReqRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitResRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.PANOnlyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.RegFormReqDataRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.RegFormReqRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CipherRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CryptoDataRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.EXHRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.MessageDigestRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.OAEPRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.SignatureRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val setRepositoryModule = module {

    factory<CipherRepository> {
        CipherRepositoryImpl(
            asymmetricCipher = get(qualifier = named(RSA)),
            symmetricCipher = get(qualifier = named(CIPHER_ALGORITHM)),
            jsonMapper = get()
        )
    }

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

    factory<PANOnlyRepository> {
        PANOnlyRepositoryImpl(
            panOnlyMapper = get(),
            jsonMapper = get()
        )
    }

    factory<RegFormReqDataRepository> {
        RegFormReqDataRepositoryImpl(
            regFormReqDataMapper = get(),
            jsonMapper = get()
        )
    }

    factory<CryptoDataRepository> {
        CryptoDataRepositoryImpl(cryptoDataMapper = get())
    }

    factory<RegFormReqRepository> {
        RegFormReqRepositoryImpl(
            panOnlyRepository = get(),
            regFormReqDataRepository = get(),
            exhRepository = get(),
            keyRepository = get(),
            messageWrapperRepository = get(),
            cryptoDataRepository = get(),
            messageWrapperUseCase = get()
        )
    }

    factory<OAEPRepository> {
        OAEPRepositoryImpl(
            oaepMapper = get(),
            cipherRepository = get()
        )
    }

    factory<EXHRepository> {
        EXHRepositoryImpl(
            jsonMapper = get(),
            cipherRepository = get(),
            keyRepository = get(),
            messageDigestRepository = get(),
            oaepRepository = get()
        )
    }
}
