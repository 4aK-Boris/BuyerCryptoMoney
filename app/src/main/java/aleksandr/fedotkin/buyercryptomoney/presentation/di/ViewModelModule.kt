package aleksandr.fedotkin.buyercryptomoney.presentation.di

import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.BankCardViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.CodeViewModel
import aleksandr.fedotkin.buyercryptomoney.core.MainViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.ProductViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BankCardViewModel(accuracyUseCase = get(), errorHandler = get(), buyUseCase = get())
    }

    viewModel {
        ProductViewModel(
            productUseCase = get(),
            buyerUseCase = get(),
            defaultValue = get(),
            errorHandler = get()
        )
    }

    viewModel {
        MainViewModel(errorHandler = get())
    }

    viewModel {
        TestViewModel()
    }

    viewModel {
        CodeViewModel(
            errorHandler = get(),
            productUseCase = get(),
            buyUseCase = get(),
            accuracyUseCase = get()
        )
    }
}
