package aleksandr.fedotkin.set.protocol.data.dto.error

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import kotlinx.serialization.Serializable

@Serializable
data class Error<T>(
    val signedError: SignedError,
    val unsignedError: UnsignedError<T>
): DTO
