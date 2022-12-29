package aleksandr.fedotkin.buyercryptomoney.data.network.plugins

import aleksandr.fedotkin.buyercryptomoney.core.TIMEOUT
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout



fun HttpClientConfig<*>.configureTimeout() {
    install(HttpTimeout) {
        requestTimeoutMillis = TIMEOUT
    }
}

