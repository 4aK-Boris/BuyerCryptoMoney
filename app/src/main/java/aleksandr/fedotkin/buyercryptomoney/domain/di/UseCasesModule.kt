package aleksandr.fedotkin.buyercryptomoney.domain.di

import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.BuyerUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.ProductUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.CertificateUseCase
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
        CertificateUseCase(certificateRepository = get())
    }
}
