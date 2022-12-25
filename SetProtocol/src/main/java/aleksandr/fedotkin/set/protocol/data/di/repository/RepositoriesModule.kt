package aleksandr.fedotkin.set.protocol.data.di.repository

import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.RSA
import aleksandr.fedotkin.set.protocol.data.repositories.ErrorRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.MessageWrapperRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.PANOnlyRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.CipherRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.CryptoDataRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.EXHRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.KeyRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.MessageDigestRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP3RepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.SignatureRepositoryImpl
import aleksandr.fedotkin.set.protocol.domain.repositories.ErrorRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.MessageWrapperRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.CipherRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.CryptoDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EXHRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.MessageDigestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP3Repository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.SignatureRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoriesModule = module {

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
            messageWrapperMapper = get(),
            jsonMapper = get(),
            messageHeaderMapper = get()
        )
    }

    factory<CardCInitReqRepository> {
        CardCInitReqRepositoryImpl(cardCInitReqMapper = get())
    }

    factory<CardCInitResRepository> {
        CardCInitResRepositoryImpl(
            cardCInitResMapper = get(),
            keyRepository = get(),
            signatureRepository = get()
        )
    }

    factory<PANOnlyRepository> {
        PANOnlyRepositoryImpl(
            panOnlyMapper = get(),
            jsonMapper = get()
        )
    }

    factory<CryptoDataRepository> {
        CryptoDataRepositoryImpl(cryptoDataMapper = get())
    }

    factory<RegFormReqRepository> {
        RegFormReqRepositoryImpl(
            panOnlyRepository = get(),
            exhRepository = get(),
            keyRepository = get(),
            regFormReqDataMapper = get()
        )
    }

    factory<OAEP3Repository> {
        OAEP3RepositoryImpl(
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

//    factory<CryptoDataRepository> {
//        CryptoDataRepositoryImpl(cryptoDataMapper = get())
//    }
}
