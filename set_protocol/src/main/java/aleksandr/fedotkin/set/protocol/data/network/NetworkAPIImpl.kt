package aleksandr.fedotkin.set.protocol.data.network

import aleksandr.fedotkin.set.protocol.core.CARD_C_INIT
import aleksandr.fedotkin.set.protocol.core.ERROR_URL
import aleksandr.fedotkin.set.protocol.core.REG_FORM

class NetworkAPIImpl(private val ktorClient: KtorClient): NetworkAPI {

    override suspend fun sendCardCInitReq(messageWrapperJson: String): String {
        return ktorClient.post(url = CARD_C_INIT, body = messageWrapperJson)
    }

    override suspend fun sendError(messageWrapperJson: String): Boolean {
        return ktorClient.post(url = ERROR_URL, body = messageWrapperJson)
    }

    override suspend fun sendRegFormReq(messageWrapperJson: String): String {
        return ktorClient.post(url = REG_FORM, body = messageWrapperJson)
    }
}