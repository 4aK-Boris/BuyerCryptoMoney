package aleksandr.fedotkin.set.protocol.data.di

import aleksandr.fedotkin.set.protocol.data.network.SetNetworkAPI
import aleksandr.fedotkin.set.protocol.data.network.SetNetworkAPIImpl
import org.koin.dsl.module

val setNetworkModule = module {

    factory<SetNetworkAPI> {
        SetNetworkAPIImpl(ktorClient = get())
    }
}