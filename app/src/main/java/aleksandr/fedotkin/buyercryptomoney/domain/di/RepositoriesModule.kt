package aleksandr.fedotkin.buyercryptomoney.domain.di

import aleksandr.fedotkin.buyercryptomoney.data.repositories.BuyRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.data.repositories.DataRepositoryImpl
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.BuyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.DataRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoriesModule = module {

    factoryOf(::BuyRepositoryImpl) {
        bind<BuyRepository>()
    }

    factoryOf(:: DataRepositoryImpl) {
        bind<DataRepository>()
    }

}