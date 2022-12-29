package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.core.DTO
import kotlinx.serialization.Serializable

@Serializable
data class Error<T: DTO>(
    val signedError: SignedError,
    val unsignedError: UnsignedError<T>
): DTO
