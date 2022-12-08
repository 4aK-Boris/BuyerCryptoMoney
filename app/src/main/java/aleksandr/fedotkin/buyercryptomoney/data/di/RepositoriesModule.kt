package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.repositories.BuyRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.BuyerRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.SellerRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.ProductRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyerRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.ProductRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.SellerRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoriesModule = module {

    factoryOf(::BuyRepositoryImpl) {
        bind<BuyRepository>()
    }

    factoryOf(::BuyerRepositoryImpl) {
        bind<BuyerRepository>()
    }

    factoryOf(::SellerRepositoryImpl) {
        bind<SellerRepository>()
    }

    factoryOf(::ProductRepositoryImpl) {
        bind<ProductRepository>()
    }
}
