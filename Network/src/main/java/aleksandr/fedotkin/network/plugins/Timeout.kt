package aleksandr.fedotkin.network.plugins

import aleksandr.fedotkin.network.core.TIMEOUT
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout

fun HttpClientConfig<*>.configureTimeout() {
    install(HttpTimeout) {
        requestTimeoutMillis = TIMEOUT
    }
}

