package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel

class MessageWrapperMapper(
    private val messageHeaderMapper: MessageHeaderMapper
) {

    fun <T, R> map(messageWrapperModel: MessageWrapperModel<T>, map: (T) -> R): MessageWrapper<R> {
        return MessageWrapper(
            messageHeader = messageHeaderMapper.map(messageHeaderModel = messageWrapperModel.messageHeaderModel),
            message = map(messageWrapperModel.messageModel),
            mWExtension = messageWrapperModel.mWExtension
        )
    }

    fun <T, R> map(messageWrapper: MessageWrapper<T>, map: (T) -> R): MessageWrapperModel<R> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapper.map(messageHeader = messageWrapper.messageHeader),
            messageModel = map(messageWrapper.message),
            mWExtension = messageWrapper.mWExtension
        )
    }

    fun <T, R> map(
        messageWrapperModel: MessageWrapperModel<ErrorModel<T>>,
        errorMap: (ErrorModel<T>, (T) -> R) -> Error<R>,
        map: (T) -> R
    ): MessageWrapper<Error<R>> {
        return MessageWrapper(
            messageHeader = messageHeaderMapper.map(messageHeaderModel = messageWrapperModel.messageHeaderModel),
            message = errorMap(messageWrapperModel.messageModel, map),
            mWExtension = messageWrapperModel.mWExtension
        )
    }

    fun <T, R> map(
        messageWrapper: MessageWrapper<Error<T>>,
        errorMap: (Error<T>, (T) -> R) -> ErrorModel<R>,
        map: (T) -> R
    ): MessageWrapperModel<ErrorModel<R>> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapper.map(messageHeader = messageWrapper.messageHeader),
            messageModel = errorMap(messageWrapper.message, map),
            mWExtension = messageWrapper.mWExtension
        )
    }
}