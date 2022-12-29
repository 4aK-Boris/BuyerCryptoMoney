package aleksandr.fedotkin.set.protocol.data.di.repository

import org.koin.dsl.module

val repositoriesModule = module {

    includes(coreRepositoriesModule, errorRepositoriesModule, messageWrapperRepositoryModule, cryptoRepositoriesModule, certificateRepositoryModule)

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
