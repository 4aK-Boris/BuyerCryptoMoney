package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.UnsignedError
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.UnsignedErrorModel

class UnsignedErrorMapper(
    private val errorTBSMapper: ErrorTBSMapper
) {

    fun <T, R> map(unsignedError: UnsignedError<T>, map: (T) -> R): UnsignedErrorModel<R> {
        return UnsignedErrorModel(errorTBSModel = errorTBSMapper.map(errorTBS = unsignedError.errorTBS, map = map))
    }

    fun <T, R> map(unsignedErrorModel: UnsignedErrorModel<T>, map: (T) -> R): UnsignedError<R> {
        return UnsignedError(errorTBS = errorTBSMapper.map(errorTBSModel = unsignedErrorModel.errorTBSModel, map = map))
    }
}