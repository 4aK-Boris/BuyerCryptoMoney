package aleksandr.fedotkin.set.protocol.data.di.repository

import aleksandr.fedotkin.set.protocol.core.di.certificate.Certificate
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapper
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResTBSRepositoryImpl
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoriesModule = module {

    includes(coreRepositoriesModule, errorRepositoriesModule, messageWrapperRepositoryModule)

    factory<CardCInitReqRepository> { CardCInitReqRepositoryImpl(mapper = get()) }

    factory<CardCInitResTBSRepository> {
        CardCInitResTBSRepositoryImpl(
            mapper = get(),
            keyRepository = get(),
            privateKey = get(qualifier = named(CertificateQualifiers.CCA)),
            certificate = get<Certificate>(qualifier = named(CertificateQualifiers.CCA)).x509Certificate,
            signatureRepository = get { parametersOf(get<CardCInitResTBSMapper>()) }
        )
    }

    factory<CardCInitResRepository> {
        CardCInitResRepositoryImpl(
            mapper = get(),
            cardCInitResTBSRepository = get()
        )
    }

//
//    factory<ErrorRepository> {
//        ErrorRepositoryImpl(
//            errorMapper = get(),
//            signatureRepository = get(),
//            keyRepository = get(),
//            errorTBSMapper = get()
//        )
//    }
//
//    factory<MessageWrapperRepository> {
//        MessageWrapperRepositoryImpl(
//            messageWrapperMapper = get(),
//            jsonMapper = get(),
//            messageHeaderMapper = get()
//        )
//    }
//
//    factory<CardCInitReqRepository> {
//        CardCInitReqRepositoryImpl(cardCInitReqMapper = get())
//    }
//
//    factory<CardCInitResRepository> {
//        CardCInitResRepositoryImpl(
//            cardCInitResMapper = get(),
//            keyRepository = get(),
//            signatureRepository = get()
//        )
//    }
//
//    factory<PANOnlyRepository> {
//        PANOnlyRepositoryImpl(panOnlyMapper = get())
//    }
//
//    factory<RegFormReqDataRepository> {
//        RegFormReqDataRepositoryImpl(regFormReqDataMapper = get())
//    }
//
//    factory<RegFormReqRepository> {
//        RegFormReqRepositoryImpl(
//            panOnlyRepository = get(),
//            exhRepository = get(),
//            keyRepository = get(),
//            regFormReqMapper = get(),
//            regFormReqDataRepository = get()
//        )
//    }
//
//    factory<OAEP3Repository> {
//        OAEP3RepositoryImpl(
//            oaepMapper = get(),
//            cipherRepository = get()
//        )
//    }
//
//    factory<EXHRepository> {
//        EXHRepositoryImpl(
//            jsonMapper = get(),
//            cipherRepository = get(),
//            keyRepository = get(),
//            messageDigestRepository = get(),
//            oaepRepository = get()
//        )
//    }
}
