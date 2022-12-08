package aleksandr.fedotkin.buyercryptomoney.presentation.di

import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.BankCardViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.MainViewModel
import aleksandr.fedotkin.buyercryptomoney.presentation.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BankCardViewModel(buyUseCase = get())
    }

    viewModel {
        ProductViewModel(productUseCase = get(), buyerUseCase = get(), defaultValue = get())
    }

    viewModel {
        MainViewModel()
    }
}
