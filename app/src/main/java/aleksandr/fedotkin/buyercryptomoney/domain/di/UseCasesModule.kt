package aleksandr.fedotkin.buyercryptomoney.domain.di

import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyerUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.ProductUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.CardCInitReqUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.CardCInitResUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.CertificateUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.ErrorUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.MessageWrapperUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.RegFormReqUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        BuyUseCase(buyRepository = get())
    }

    factory {
        ProductUseCase(productRepository = get())
    }

    factory {
        BuyerUseCase(buyerRepository = get())
    }

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
        MessageWrapperUseCase(
            messageWrapperRepository = get(),
            errorUseCase = get()
        )
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
