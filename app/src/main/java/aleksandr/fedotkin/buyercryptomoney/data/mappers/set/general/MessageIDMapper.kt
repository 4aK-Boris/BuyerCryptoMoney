package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageID
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageIDModel

class MessageIDMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(messageIdModel: MessageIDModel?): MessageID? {
        return messageIdModel?.let {
            return MessageID(
                lIdC = bigIntegerMapper.map(number = it.lIdC),
                lIdM = bigIntegerMapper.map(number = it.lIdM),
                xId = bigIntegerMapper.map(number = it.xId)
            )
        }
    }

    fun map(messageId: MessageID?): MessageIDModel? {
        return messageId?.let {
            MessageIDModel(
                lIdC = bigIntegerMapper.map(string = it.lIdC),
                lIdM = bigIntegerMapper.map(string = it.lIdM),
                xId = bigIntegerMapper.map(string = it.xId)
            )
        }
    }
}