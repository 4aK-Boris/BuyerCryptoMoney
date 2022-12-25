package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel

class ErrorTBSMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val bigIntegerMapper: BigIntegerMapper,
    private val errorMsgMapper: ErrorMsgMapper
) {

    fun <T, R> map(dto: ErrorTBS<T>, map: (T) -> R): ErrorTBSModel<R> {
        return ErrorTBSModel(
            errorCode = dto.errorCode,
            errorNonce = bigIntegerMapper.map(string = dto.errorNonce),
            errorOID = dto.errorOID,
            errorThumb = byteArrayMapper.map(string = dto.errorThumb),
            errorMsgModel = errorMsgMapper.map(dto = dto.errorMsg, map = map)
        )
    }

    fun <T, R> map(model: ErrorTBSModel<T>, map: (T) -> R): ErrorTBS<R> {
        return ErrorTBS(
            errorCode = model.errorCode,
            errorNonce = bigIntegerMapper.map(number = model.errorNonce),
            errorOID = model.errorOID,
            errorThumb = byteArrayMapper.map(byteArray = model.errorThumb),
            errorMsg = errorMsgMapper.map(model = model.errorMsgModel, map = map)
        )
    }
}
