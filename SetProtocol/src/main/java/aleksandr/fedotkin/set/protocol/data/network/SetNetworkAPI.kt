package aleksandr.fedotkin.set.protocol.data.network

interface SetNetworkAPI {

    suspend fun sendCardCInitReq(messageWrapperJson: String): String

    suspend fun sendError(messageWrapperJson: String): Boolean

    suspend fun sendRegFormReq(messageWrapperJson: String): String

    suspend fun sendCerReq(messageWrapperJson: String): String
}
