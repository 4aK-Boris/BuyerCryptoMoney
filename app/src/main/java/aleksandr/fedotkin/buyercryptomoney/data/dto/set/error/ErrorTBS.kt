package aleksandr.fedotkin.buyercryptomoney.data.dto.set.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorTBS<T>(
    val errorCode: ErrorCode,
    val errorNonce: String,
    val errorOID: String?,
    val errorThumb: String?,
    val errorMsg: ErrorMsg<T>
)
