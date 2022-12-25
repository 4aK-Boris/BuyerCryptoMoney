package aleksandr.fedotkin.network.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.websocket.WebSockets

fun HttpClientConfig<*>.configureWebsockets() {
    install(WebSockets)
}