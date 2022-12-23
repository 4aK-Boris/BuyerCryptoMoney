package aleksandr.fedotkin.set.protocol.data.network

interface NetworkAPI {

    suspend fun sendCardCInitReq(messageWrapperJson: String): String

    suspend fun sendError(messageWrapperJson: String): Boolean

    suspend fun sendRegFormReq(messageWrapperJson: String): String
}
