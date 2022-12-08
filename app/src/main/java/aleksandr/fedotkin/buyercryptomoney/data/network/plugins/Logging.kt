package aleksandr.fedotkin.buyercryptomoney.data.network.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE

fun HttpClientConfig<*>.configureLogging() {
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }
}
