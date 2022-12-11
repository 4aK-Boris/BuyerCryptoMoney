package aleksandr.fedotkin.buyercryptomoney.domain.model.set.error

import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.SignedErrorModel

data class ErrorModel<T>(
    val signedErrorModel: SignedErrorModel,
    val unsignedErrorModel: UnsignedErrorModel<T>
)
