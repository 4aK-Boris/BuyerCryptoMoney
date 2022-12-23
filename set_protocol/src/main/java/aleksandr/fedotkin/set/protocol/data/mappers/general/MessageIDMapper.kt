package aleksandr.fedotkin.set.protocol.data.mappers.general

import aleksandr.fedotkin.set.protocol.data.dto.general.MessageID
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel

class MessageIDMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(model: MessageIDModel?): MessageID? {
        return model?.let {
            return MessageID(
                lIdC = bigIntegerMapper.map(number = it.lIdC),
                lIdM = bigIntegerMapper.map(number = it.lIdM),
                xId = bigIntegerMapper.map(number = it.xId)
            )
        }
    }

    fun map(dto: MessageID?): MessageIDModel? {
        return dto?.let {
            MessageIDModel(
                lIdC = bigIntegerMapper.map(string = it.lIdC),
                lIdM = bigIntegerMapper.map(string = it.lIdM),
                xId = bigIntegerMapper.map(string = it.xId)
            )
        }
    }
}
