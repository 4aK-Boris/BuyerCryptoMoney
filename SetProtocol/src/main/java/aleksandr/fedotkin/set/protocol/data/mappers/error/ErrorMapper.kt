package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import aleksandr.fedotkin.set.protocol.data.dto.error.Error

class ErrorMapper(
    private val signedErrorMapper: SignedErrorMapper,
    private val unsignedErrorMapper: UnsignedErrorMapper
) {

    fun <T, R> map(dto: Error<T>, map: (T) -> R): ErrorModel<R> {
        return ErrorModel(
            signedErrorModel = signedErrorMapper.map(dto = dto.signedError),
            unsignedErrorModel = unsignedErrorMapper.map(dto = dto.unsignedError, map = map)
        )
    }

    fun <T, R> map(model: ErrorModel<T>, map: (T) -> R): Error<R> {
        return Error(
            signedError = signedErrorMapper.map(model = model.signedErrorModel),
            unsignedError = unsignedErrorMapper.map(model = model.unsignedErrorModel, map = map)
        )
    }
}
