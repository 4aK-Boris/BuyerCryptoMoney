package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.core.Model

data class ErrorModel<T: Model>(
    val signedError: SignedErrorModel,
    val unsignedError: UnsignedErrorModel<T>
): Model
