package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error

class ErrorMapper(
    private val signedErrorMapper: SignedErrorMapper,
    private val unsignedErrorMapper: UnsignedErrorMapper
) {

    fun <T, R> map(error: Error<T>, map: (T) -> R): ErrorModel<R> {
        return ErrorModel(
            signedErrorModel = signedErrorMapper.map(signedError = error.signedError),
            unsignedErrorModel = unsignedErrorMapper.map(unsignedError = error.unsignedError, map = map)
        )
    }

    fun <T, R> map(errorModel: ErrorModel<T>, map: (T) -> R): Error<R> {
        return Error(
            signedError = signedErrorMapper.map(signedErrorModel = errorModel.signedErrorModel),
            unsignedError = unsignedErrorMapper.map(unsignedErrorModel = errorModel.unsignedErrorModel, map = map)
        )
    }
}