package aleksandr.fedotkin.buyercryptomoney.data.dto.set.error

import kotlinx.serialization.Serializable

@Serializable
data class Error<T>(
    val signedError: SignedError,
    val unsignedError: UnsignedError<T>
)
