package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageID
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel
import kotlinx.serialization.KSerializer

class MessageIDMapper(
    private val bigIntegerMapper: BigIntegerMapper
) : SetMapper<MessageIDModel, MessageID> {

    override val serializer: KSerializer<MessageID>
        get() = MessageID.serializer()

    override fun map(value: MessageIDModel): MessageID {
        return MessageID(
            lIdC = value.lIdC?.let { bigIntegerMapper.map(value = it) },
            lIdM = value.lIdM?.let { bigIntegerMapper.map(value = it) },
            xId = value.xId?.let { bigIntegerMapper.map(value = it) }
        )
    }

    override fun reverseMap(value: MessageID): MessageIDModel {
        return MessageIDModel(
            lIdC = value.lIdC?.let { bigIntegerMapper.reverseMap(value = it) },
            lIdM = value.lIdM?.let { bigIntegerMapper.reverseMap(value = it) },
            xId = value.xId?.let { bigIntegerMapper.reverseMap(value = it) }
        )
    }
}
