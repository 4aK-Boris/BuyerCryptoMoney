package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.data.dto.general.MessageHeader
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateTimeMapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel

class MessageHeaderMapper(
    private val messageIDMapper: MessageIDMapper,
    private val bigIntegerMapper: BigIntegerMapper,
    private val dateTimeMapper: DateTimeMapper
) {

    fun map(model: MessageHeaderModel): MessageHeader {
        return MessageHeader(
            version = model.version,
            revision = model.revision,
            messageID = messageIDMapper.map(model = model.messageIDModel),
            rrpId = bigIntegerMapper.map(number = model.rrpId),
            sWIdent = model.sWIdent,
            date = dateTimeMapper.map(dateTime = model.date)
        )
    }

    fun map(dto: MessageHeader): MessageHeaderModel {
        return MessageHeaderModel(
            version = dto.version,
            revision = dto.revision,
            messageIDModel = messageIDMapper.map(dto = dto.messageID),
            rrpId = bigIntegerMapper.map(string = dto.rrpId),
            sWIdent = dto.sWIdent,
            date = dateTimeMapper.map(dateTime = dto.date)
        )
    }
}
