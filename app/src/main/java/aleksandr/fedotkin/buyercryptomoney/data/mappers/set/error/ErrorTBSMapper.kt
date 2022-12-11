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

    fun <T, R> map(errorTBS: ErrorTBS<T>, map: (T) -> R): ErrorTBSModel<R> {
        return ErrorTBSModel(
            errorCode = errorTBS.errorCode,
            errorNonce = bigIntegerMapper.map(string = errorTBS.errorNonce),
            errorOID = errorTBS.errorOID,
            errorThumb = byteArrayMapper.map(string = errorTBS.errorThumb),
            errorMsgModel = errorMsgMapper.map(errorMsg = errorTBS.errorMsg, map = map)
        )
    }

    fun <T, R> map(errorTBSModel: ErrorTBSModel<T>, map: (T) -> R): ErrorTBS<R> {
        return ErrorTBS(
            errorCode = errorTBSModel.errorCode,
            errorNonce = bigIntegerMapper.map(number = errorTBSModel.errorNonce),
            errorOID = errorTBSModel.errorOID,
            errorThumb = byteArrayMapper.map(byteArray = errorTBSModel.errorThumb),
            errorMsg = errorMsgMapper.map(errorMsgModel = errorTBSModel.errorMsgModel, map = map)
        )
    }
}