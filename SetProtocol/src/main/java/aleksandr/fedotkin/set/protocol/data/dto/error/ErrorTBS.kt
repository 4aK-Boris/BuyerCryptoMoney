package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
data class ErrorTBS<T: DTO>(
    val errorCode: ErrorCode,
    val errorNonce: String,
    val errorOID: String?,
    val errorThumb: String,
    val errorMsg: ErrorMsg<T>
): DTO
