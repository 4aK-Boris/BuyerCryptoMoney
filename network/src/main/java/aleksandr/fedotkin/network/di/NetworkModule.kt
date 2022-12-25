package aleksandr.fedotkin.network

import aleksandr.fedotkin.network.network.KtorClient
import aleksandr.fedotkin.network.plugins.configureDefaultRequest
import aleksandr.fedotkin.network.plugins.configureJson
import aleksandr.fedotkin.network.plugins.configureLogging
import aleksandr.fedotkin.network.plugins.configureTimeout
import aleksandr.fedotkin.network.plugins.configureWebsockets
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
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

    factory {
        KtorClient(client = get())
    }
}