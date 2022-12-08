package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.network.KtorClient
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPIImpl
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureDefaultRequest
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureLogging
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureJson
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureTimeout
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(Android) {
            configureJson()
            configureLogging()
            configureDefaultRequest()
            configureTimeout()
        }
    }

    single {
        KtorClient(client = get())
    }

    singleOf(::NetworkAPIImpl) {
        bind<NetworkAPI>()
    }
}
