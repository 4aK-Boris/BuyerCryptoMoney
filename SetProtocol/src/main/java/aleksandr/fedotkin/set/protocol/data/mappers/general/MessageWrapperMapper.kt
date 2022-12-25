package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel

class MessageWrapperMapper(
    private val messageHeaderMapper: MessageHeaderMapper
) {

    fun <T: Model, R: DTO> map(model: MessageWrapperModel<T>, map: (T) -> R): MessageWrapper<R> {
        return MessageWrapper(
            messageHeader = messageHeaderMapper.map(model = model.messageHeaderModel),
            message = map(model.messageModel),
            mWExtension = model.mWExtension
        )
    }

    fun <T: Model, R: DTO> map(dto: MessageWrapper<R>, map: (R) -> T): MessageWrapperModel<T> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapper.map(dto = dto.messageHeader),
            messageModel = map(dto.message),
            mWExtension = dto.mWExtension
        )
    }
}
