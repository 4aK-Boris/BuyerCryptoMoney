package aleksandr.fedotkin.network.core.di

import aleksandr.fedotkin.network.KtorClient
import aleksandr.fedotkin.network.plugins.configureDefaultRequest
import aleksandr.fedotkin.network.plugins.configureJson
import aleksandr.fedotkin.network.plugins.configureLogging
import aleksandr.fedotkin.network.plugins.configureTimeout
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
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

    factory {
        KtorClient(client = get())
    }
}