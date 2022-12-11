package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorMsg
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageHeaderMapper
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.ErrorMsgModel

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
