package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.network.KtorClient
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPIImpl
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureDefaultRequest
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureLogging
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureJson
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureTimeout
import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.configureWebsockets
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(OkHttp) {
            configureJson()
            configureLogging()
            configureDefaultRequest()
            configureTimeout()
            configureWebsockets()
        }
    }

    single {
        KtorClient(client = get())
    }

    singleOf(::NetworkAPIImpl) {
        bind<NetworkAPI>()
    }
}
