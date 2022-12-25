package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPIImpl
import org.koin.dsl.module

val mainNetworkModule = module {

    single<NetworkAPI> {
        NetworkAPIImpl(ktorClient = get())
    }
}
