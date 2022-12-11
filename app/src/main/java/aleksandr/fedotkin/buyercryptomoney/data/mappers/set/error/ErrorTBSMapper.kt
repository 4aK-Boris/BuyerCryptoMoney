package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorTBSModel

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
