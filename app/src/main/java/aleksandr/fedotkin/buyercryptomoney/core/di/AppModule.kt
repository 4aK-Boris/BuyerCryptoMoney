package aleksandr.fedotkin.buyercryptomoney.core.di

import aleksandr.fedotkin.buyercryptomoney.data.di.mapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.repositoriesModule
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPIImpl
import aleksandr.fedotkin.buyercryptomoney.domain.di.errorModule
import aleksandr.fedotkin.buyercryptomoney.domain.di.useCasesModule
import aleksandr.fedotkin.buyercryptomoney.presentation.di.mockModule
import aleksandr.fedotkin.buyercryptomoney.presentation.di.viewModelModule
import aleksandr.fedotkin.core.di.coreModule
import aleksandr.fedotkin.network.core.di.networkModule
import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        viewModelModule,
        useCasesModule,
        repositoriesModule,
        mapperModule,
        mockModule,
        errorModule,
        coreModule
    )

    single {
        single<NetworkAPI> {
            NetworkAPIImpl(ktorClient = get())
        }
    }
}
