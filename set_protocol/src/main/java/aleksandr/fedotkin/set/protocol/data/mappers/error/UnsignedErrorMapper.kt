package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.data.dto.error.UnsignedError
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel

class UnsignedErrorMapper(
    private val errorTBSMapper: ErrorTBSMapper
) {

    fun <T, R> map(dto: UnsignedError<T>, map: (T) -> R): UnsignedErrorModel<R> {
        return UnsignedErrorModel(errorTBSModel = errorTBSMapper.map(dto = dto.errorTBS, map = map))
    }

    fun <T, R> map(model: UnsignedErrorModel<T>, map: (T) -> R): UnsignedError<R> {
        return UnsignedError(errorTBS = errorTBSMapper.map(model = model.errorTBSModel, map = map))
    }
}
