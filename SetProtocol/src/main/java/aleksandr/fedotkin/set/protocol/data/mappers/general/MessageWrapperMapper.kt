package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import kotlinx.serialization.KSerializer

class MessageWrapperMapper<T: Model, R: DTO>(
    private val messageHeaderMapper: MessageHeaderMapper,
    private val mapper: SetMapper<T, R>
): SetMapper<MessageWrapperModel<T>, MessageWrapper<R>> {

    override val serializer: KSerializer<MessageWrapper<R>>
        get() = MessageWrapper.serializer(mapper.serializer)

    override fun map(value: MessageWrapperModel<T>): MessageWrapper<R> {
        return MessageWrapper(
            messageHeader = messageHeaderMapper.map(value = value.messageHeaderModel),
            message = mapper.map(value = value.messageModel),
            mWExtension = value.mWExtension
        )
    }

    override fun reverseMap(value: MessageWrapper<R>): MessageWrapperModel<T> {
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderMapper.reverseMap(value = value.messageHeader),
            messageModel = mapper.reverseMap(value = value.message),
            mWExtension = value.mWExtension
        )
    }
}
