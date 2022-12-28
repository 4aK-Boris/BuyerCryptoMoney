package aleksandr.fedotkin.set.protocol.data.network

interface SetNetworkAPI {

    suspend fun sendCardCInit(json: String): String

    suspend fun sendError(json: String): Boolean
}
