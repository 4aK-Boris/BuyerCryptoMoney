package aleksandr.fedotkin.set.protocol.data.di

import aleksandr.fedotkin.set.protocol.data.network.NetworkAPI
import aleksandr.fedotkin.set.protocol.data.network.NetworkAPIImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    singleOf(::NetworkAPIImpl) {
        bind<NetworkAPI>()
    }
}