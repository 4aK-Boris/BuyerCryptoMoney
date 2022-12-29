package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorMsg
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel
import kotlinx.serialization.KSerializer

class ErrorMsgMapper<T: Model, R: DTO>(
    private val messageHeaderMapper: MessageHeaderMapper,
    private val mapper: SetMapper<T, R>
): SetMapper<ErrorMsgModel<T>, ErrorMsg<R>> {

    override val serializer: KSerializer<ErrorMsg<R>>
        get() = ErrorMsg.serializer(mapper.serializer)

    override fun map(value: ErrorMsgModel<T>): ErrorMsg<R> {
        return ErrorMsg(
            messageHeader = messageHeaderMapper.map(value = value.messageHeader),
            badWrapper = mapper.map(value = value.badWrapper)
        )
    }

    override fun reverseMap(value: ErrorMsg<R>): ErrorMsgModel<T> {
        return ErrorMsgModel(
            messageHeader = messageHeaderMapper.reverseMap(value = value.messageHeader),
            badWrapper = mapper.reverseMap(value = value.badWrapper)
        )
    }
}
