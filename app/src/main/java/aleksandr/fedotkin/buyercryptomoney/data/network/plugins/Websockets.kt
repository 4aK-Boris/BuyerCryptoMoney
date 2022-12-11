package aleksandr.fedotkin.buyercryptomoney.data.network.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.websocket.WebSockets

fun HttpClientConfig<*>.configureWebsockets() {
    install(WebSockets)
}