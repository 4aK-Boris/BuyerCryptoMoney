package aleksandr.fedotkin.set.protocol.domain.di

import aleksandr.fedotkin.set.protocol.domain.useceses.CardCInitReqUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.CardCInitResUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.CertificateUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.ErrorUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.MessageWrapperUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.RegFormReqUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        CertificateUseCase(
            cardCInitReqUseCase = get(),
            cardCInitResUseCase = get(),
            regFormReqUseCase = get()
        )
    }

    factory {
        ErrorUseCase(
            errorRepository = get(),
            networkAPI = get(),
            messageWrapperRepository = get(),
            messageWrapperUseCase = get()
        )
    }

    factory {
        MessageWrapperUseCase(messageWrapperRepository = get())
    }

    factory {
        CardCInitReqUseCase(
            cardCInitReqRepository = get(),
            networkAPI = get()
        )
    }

    factory {
        CardCInitResUseCase(
            messageWrapperUseCase = get(),
            cardCInitResRepository = get(),
            errorUseCase = get(),
            keyRepository = get()
        )
    }

    factory {
        RegFormReqUseCase(regFormReqRepository = get(), networkAPI = get())
    }
}