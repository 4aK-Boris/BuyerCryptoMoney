package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelMapper
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageWrapperMapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageHeaderRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.time.LocalDateTime

class MessageWrapperRepositoryImpl<T : Model, R : DTO>(
    private val jsonModelMapper: JsonModelMapper<MessageWrapperModel<T>, MessageWrapper<R>>,
    private val messageHeaderRepository: MessageHeaderRepository,
    override val mapper: MessageWrapperMapper<T, R>
) : MessageWrapperRepository<T, R> {

    override suspend fun <S : Model> changeMessageModelOnOther(
        rrpid: BigInteger, messageModel: S, messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<S> {
        return changeMessageModelOnOther(messageModel, changeRRPID(rrpid, messageWrapperModel))
    }

    override suspend fun changeMessageModel(
        rrpid: BigInteger,
        messageModel: T,
        messageWrapperModel: MessageWrapperModel<*>,
    ): MessageWrapperModel<T> {
        return changeRRPID(rrpid, changeMessageModel(messageModel, messageWrapperModel))
    }

    override suspend fun <S : Model> changeMessageModelOnOther(
        messageModel: S, messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<S> {
        return MessageWrapperModel(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    override suspend fun changeMessageModel(
        messageModel: T, messageWrapperModel: MessageWrapperModel<*>
    ): MessageWrapperModel<T> {
        return MessageWrapperModel(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    override suspend fun <S : Model> changeRRPID(
        rrpid: BigInteger,
        messageWrapperModel: MessageWrapperModel<S>,
    ): MessageWrapperModel<S> {
        return messageWrapperModel.copy(
            messageHeaderModel = messageWrapperModel.messageHeaderModel.copy(
                rrpId = rrpid, date = LocalDateTime.now()
            )
        )
    }

    override suspend fun create(rrpid: BigInteger, message: T): MessageWrapperModel<T> {
        return MessageWrapperModel(
            mWExtension = null,
            messageModel = message,
            messageHeaderModel = messageHeaderRepository.create(rrpid = rrpid)
        )
    }

    override suspend fun messageWrapperModelToJson(
        messageWrapperModel: MessageWrapperModel<T>,
    ): String {
        return jsonModelMapper.map(value = messageWrapperModel)
    }

    override suspend fun jsonToMessageWrapperModel(
        json: String,
    ): MessageWrapperModel<T> {
        return jsonModelMapper.reverseMap(value = json)
    }
}
