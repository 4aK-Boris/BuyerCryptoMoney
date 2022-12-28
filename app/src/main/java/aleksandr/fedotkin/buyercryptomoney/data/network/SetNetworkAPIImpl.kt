package aleksandr.fedotkin.buyercryptomoney.data.network

import aleksandr.fedotkin.network.network.KtorClient
import aleksandr.fedotkin.set.protocol.core.CARD_C_INIT
import aleksandr.fedotkin.set.protocol.core.ERROR
import aleksandr.fedotkin.set.protocol.data.network.SetNetworkAPI

class SetNetworkAPIImpl(private val ktorClient: KtorClient) : SetNetworkAPI {

    override suspend fun sendCardCInit(json: String): String {
        return ktorClient.postSet(url = CARD_C_INIT, json = json)
    }

    override suspend fun sendError(json: String): Boolean {
        return ktorClient.postSet(url = ERROR, json = json)
    }
}