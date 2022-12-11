package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageHeader
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.DateTimeMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel

class MessageHeaderMapper(
    private val messageIDMapper: MessageIDMapper,
    private val bigIntegerMapper: BigIntegerMapper,
    private val dateTimeMapper: DateTimeMapper
) {

    fun map(messageHeaderModel: MessageHeaderModel): MessageHeader {
        return MessageHeader(
            version = messageHeaderModel.version,
            revision = messageHeaderModel.revision,
            messageID = messageIDMapper.map(messageIdModel = messageHeaderModel.messageIDModel),
            rrpId = bigIntegerMapper.map(number = messageHeaderModel.rrpId),
            sWIdent = messageHeaderModel.sWIdent,
            date = dateTimeMapper.map(dateTime = messageHeaderModel.date)
        )
    }

    fun map(messageHeader: MessageHeader): MessageHeaderModel {
        return MessageHeaderModel(
            version = messageHeader.version,
            revision = messageHeader.revision,
            messageIDModel = messageIDMapper.map(messageId = messageHeader.messageID),
            rrpId = bigIntegerMapper.map(string = messageHeader.rrpId),
            sWIdent = messageHeader.sWIdent,
            date = dateTimeMapper.map(dateTime = messageHeader.date)
        )
    }
}