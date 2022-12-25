package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class ErrorModel<T>(
    val signedErrorModel: SignedErrorModel,
    val unsignedErrorModel: UnsignedErrorModel<T>
): Model
