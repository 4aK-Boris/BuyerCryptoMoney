package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class ErrorTBS<T>(
    val errorCode: ErrorCode,
    val errorNonce: String,
    val errorOID: String?,
    val errorThumb: String?,
    val errorMsg: ErrorMsg<T>
): DTO
