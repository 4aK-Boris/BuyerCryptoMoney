package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel

class MessageWrapperMapper(
    private val messageHeaderMapper: MessageHeaderMapper
) {

    fun <T, R> map(model: MessageWrapperModel<T>, map: (T) -> R): MessageWrapper<R> {
        return MessageWrapper(
            messageHeader = messageHeaderMapper.map(model = model.messageHeaderModel),
            message = map(model.messageModel),
            mWExtension = model.mWExtension
        )
    }

    fun <T, R> map(dto: MessageWrapper<T>, map: (T) -> R): MessageWrapperModel<R> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapper.map(dto = dto.messageHeader),
            messageModel = map(dto.message),
            mWExtension = dto.mWExtension
        )
    }

    fun <T, R> map(
        model: MessageWrapperModel<ErrorModel<T>>,
        errorMap: (ErrorModel<T>, (T) -> R) -> Error<R>,
        map: (T) -> R
    ): MessageWrapper<Error<R>> {
        return MessageWrapper(
            messageHeader = messageHeaderMapper.map(model = model.messageHeaderModel),
            message = errorMap(model.messageModel, map),
            mWExtension = model.mWExtension
        )
    }

    fun <T, R> map(
        dto: MessageWrapper<Error<T>>,
        errorMap: (Error<T>, (T) -> R) -> ErrorModel<R>,
        map: (T) -> R
    ): MessageWrapperModel<ErrorModel<R>> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapper.map(dto = dto.messageHeader),
            messageModel = errorMap(dto.message, map),
            mWExtension = dto.mWExtension
        )
    }
}
