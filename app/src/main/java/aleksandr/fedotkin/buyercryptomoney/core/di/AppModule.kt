package aleksandr.fedotkin.buyercryptomoney.core.di

import aleksandr.fedotkin.buyercryptomoney.data.di.mainNetworkModule
import aleksandr.fedotkin.buyercryptomoney.data.di.mapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.repositoriesModule
import aleksandr.fedotkin.buyercryptomoney.domain.di.useCasesModule
import aleksandr.fedotkin.buyercryptomoney.presentation.di.mockModule
import aleksandr.fedotkin.buyercryptomoney.presentation.di.viewModelModule
import aleksandr.fedotkin.core.di.coreModule
import aleksandr.fedotkin.network.core.di.networkModule
import aleksandr.fedotkin.set.protocol.core.di.setModule
import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        viewModelModule,
        useCasesModule,
        repositoriesModule,
        mapperModule,
        mockModule,
        setModule,
        mainNetworkModule,
        coreModule
    )
}
