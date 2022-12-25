package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
class UnsignedError<T>(
    val errorTBS: ErrorTBS<T>
): DTO