package aleksandr.fedotkin.set.protocol.domain.di

import aleksandr.fedotkin.set.protocol.domain.useceses.CardCInitReqUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.CardCInitResUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.CertificateUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.CryptoDataUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.RegFormResUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        CertificateUseCase(
            cardCInitReqUseCase = get(),
            cardCInitResUseCase = get(),
            //regFormReqUseCase = get()
        )
    }

    factory { CardCInitReqUseCase(cardCInitReqRepository = get())
    }

    factory {
        CardCInitResUseCase(cardCInitResRepository = get(),)
    }

    factory {
        CryptoDataUseCase(cryptoDataRepository = get())
    }

//    factory {
//        RegFormReqUseCase(regFormReqRepository = get(), cryptoDataUseCase = get())
//    }

    factory {
        RegFormResUseCase(regFormResRepository = get())
    }
}