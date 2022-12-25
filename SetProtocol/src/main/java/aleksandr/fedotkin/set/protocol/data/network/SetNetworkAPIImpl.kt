package aleksandr.fedotkin.set.protocol.data.network

import aleksandr.fedotkin.network.network.KtorClient
import aleksandr.fedotkin.set.protocol.core.CARD_C_INIT
import aleksandr.fedotkin.set.protocol.core.CERT_REQ
import aleksandr.fedotkin.set.protocol.core.ERROR_URL
import aleksandr.fedotkin.set.protocol.core.REG_FORM

class SetNetworkAPIImpl(private val ktorClient: KtorClient) : SetNetworkAPI {

    override suspend fun sendCardCInitReq(messageWrapperJson: String): String {
        return ktorClient.postSet(url = CARD_C_INIT, body = messageWrapperJson)
    }

    override suspend fun sendError(messageWrapperJson: String): Boolean {
        return ktorClient.postSet(url = ERROR_URL, body = messageWrapperJson)
    }

    override suspend fun sendRegFormReq(messageWrapperJson: String): String {
        return ktorClient.postSet(url = REG_FORM, body = messageWrapperJson)
    }

    override suspend fun sendCerReq(messageWrapperJson: String): String {
        return ktorClient.postSet(url = CERT_REQ, body = messageWrapperJson)
    }
}
