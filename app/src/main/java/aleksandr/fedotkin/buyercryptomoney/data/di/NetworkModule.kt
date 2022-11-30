package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.BASE_URL
import aleksandr.fedotkin.buyercryptomoney.data.network.KtorClient
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPIImpl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(Android) {
            configureJson()
            configureLogging()
            configureDefaultRequest()
        }
    }

    single {
        KtorClient(client = get())
    }

    singleOf(::NetworkAPIImpl){ bind<NetworkAPI>() }
}

val defaultHeaders: MutableMap<String, String> = mutableMapOf()

private fun HttpClientConfig<*>.configureJson() {
    install(ContentNegotiation) {
        json()
    }
}

private fun HttpClientConfig<*>.configureLogging() {
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }
}

private fun HttpClientConfig<*>.configureDefaultRequest() {
    defaultRequest {
        url.takeFrom(URLBuilder().takeFrom(BASE_URL).apply {
            encodedPath += url.encodedPath
        })
        headers.clear()
        defaultHeaders.forEach {
            headers.append(it.key, it.value)
        }
    }
}