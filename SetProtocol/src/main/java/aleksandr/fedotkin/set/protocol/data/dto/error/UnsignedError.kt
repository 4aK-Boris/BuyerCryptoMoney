package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
class UnsignedError<T: DTO>(
    val errorTBS: ErrorTBS<T>
): DTO