package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorMsg
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel

class ErrorMsgMapper(
    private val messageHeaderMapper: MessageHeaderMapper
) {

    fun <T, R> map(dto: ErrorMsg<T>, map: (T) -> R): ErrorMsgModel<R> {
        return ErrorMsgModel(
            messageHeader = messageHeaderMapper.map(dto = dto.messageHeader),
            badWrapper = map(dto.badWrapper)
        )
    }

    fun <T, R> map(model: ErrorMsgModel<T>, map: (T) -> R): ErrorMsg<R> {
        return ErrorMsg(
            messageHeader = messageHeaderMapper.map(model = model.messageHeader),
            badWrapper = map(model.badWrapper)
        )
    }
}
