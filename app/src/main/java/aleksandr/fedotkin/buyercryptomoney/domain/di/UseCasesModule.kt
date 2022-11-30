package aleksandr.fedotkin.buyercryptomoney.domain.di

import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.DataUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        BuyUseCase(buyRepository = get())
    }

    factory {
        DataUseCase(dataRepository = get())
    }
}